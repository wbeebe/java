/*
Copyright (c) 2018 William H. Beebe, Jr.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package visualorchestrator;

import java.io.Serializable;

/*
 * 
 */

/**
 * An activity is a single action that can be called upon to do some task or
 * work. It is a part of a Node; Node has-an Action.
 * 
 * @author wbeebe
 */
public abstract class Activity implements Serializable {
    /**
     * The internal states of an Activity.
     * 
     * UNKNOWN is when the Activity is first created. It is up to the derived
     * Activity to change this to one of the following states. It is up to the
     * class which has-an Activity to actually check the Activity's status
     * before attempting to do anything with it.
     * 
     * INITIALISED is the state of an Activity after creation or when the init()
     * method is called. It is up to the child of Activity to set its internal
     * status from UNKNOWN to INITIALIZED when created or when the init()
     * method is called. The Activity is considered to be in an idle state and
     * not RUNNING.
     * 
     * RUNNING is the state of an Activity after a call to start() or run().
     * 
     * STOPPED is the state of an Activity after a call to the stop()
     * method. STOPPED means that any processing has been completely stopped.
     * An activity that is STOPPED must be re-initialized with a call to
     * init().
     * 
     * PAUSED is the state of an Activity after a call to the pause()
     * method. PAUSED means that the Activity is simply paused, and maintains
     * it's internal state. A PAUSED Activity may be set back to RUNNING, or
     * it may be STOPPED, RESET or INITIALIZED. A call to start() is considered
     * an error.
     * 
     * RESET is the state of an Activity after a call to the reset()
     * method. RESET means that the Activity is idle and not in a RUNNING state,
     * but it is not quite as drastic as an INITIALIZED state. It can be
     * restarted with a call to start().
     * 
     * ERROR is the state of an Activity if it has encountered an internal
     * error that stops it from running. A call to getErrorMessage should
     * return a string-ified error message, suitable for logging or further
     * processing by the host application.
     * 
     * NOTE: Children of Activity should never throw exceptions; all exceptions
     * should be caught, and if of sufficient importance, set Status to ERROR
     * and create an error message to be returned by a call to getErrorMessage()
     * 
     */
    public enum Status {
        UNKNOWN,
        INITIALIZED,
        RUNNING,
        STOPPED,
        PAUSED,
        RESET,
        ERROR
    };

    private Status status;
    private Action action;

    /**
     * During initialization status is set to UKNOWN and the action is
     * set to DefaultNoOpAction, rather than leaving it null.
     */
    public Activity() {
        status = Status.UNKNOWN;
        action = new DefaultNoOpAction();
    }

    public abstract void init();
    public abstract void start();
    public abstract void stop();
    public abstract void pause();
    public abstract void run();
    public abstract void reset();

    public Status getStatus() { return status; };

    public abstract String getErrorMessage();

    public void setActionCallback(Action action) {
        this.action = action;
    }

    /**
     * A convenience method to call Action::response with the activity's
     * status as an argument. The reference is checked to see if it's non-null
     * before attempting to use it. This allows for async notification of when
     * the activity is finished performing some task, either singly or
     * repeatedly. Activity children should call this method instead of 
     * Action::response directly.
     */
    private void callActionCallback() {
        if (action != null) { action.response(status); }
    }
}
