#!/usr/bin/env bash

NETBEANS_VERSION=10
NETBEANS_APPLICATION_LOCATION="${HOME}/Applications/NetBeans/NetBeans ${NETBEANS_VERSION}.app/Contents"

# Check to see that a file name was passed on the command line.
# If not, exit with helpful message.
#
scriptName=${0##*/}
scriptName=${scriptName%.*}
if [[ $# -ne 1 ]]; then
    echo
    echo "$scriptName is a Bash script meant to install the Apache NetBeans releases"
    echo "as a macOS application that can be launched via LaunchPad."
    echo
    echo "NetBeans is installed in your local Application folder, $HOME/Applications."
    echo "It does not required root to operate, and thus does not require sudo."
    echo
    echo "You need to provide a NetBeans zip file for $scriptName to work with."
    echo "Exiting."
    exit 1
fi

# Check to see if the file exists and we can at least read it.
# If not, exit with helpful message.
#
if [[ ! -f $1 ]]; then
    echo "File '$1' does not exist."
    echo "Exiting."
    exit 2
else
    echo "Will install from file '$1'."
fi 

# Prepare the area where the NetBeans app will be built from the basic
# zip file. Create the basic directory structure.
#
mkdir -p "$NETBEANS_APPLICATION_LOCATION/MacOS"
mkdir -p "$NETBEANS_APPLICATION_LOCATION/Resources"

# Use heredoc syntax to output the Info.plist file. Use the NETBEANS_VERSION variable
# set earlier to set it throughout the plist file before outputing it.
#
cat > "$NETBEANS_APPLICATION_LOCATION/Info.plist" << __EOIP__
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist SYSTEM "file://localhost/System/Library/DTDs/PropertyList.dtd">
<plist version="1.0">
  <dict>
    <key>CFBundleName</key>
    <string>NetBeans $NETBEANS_VERSION</string>

    <key>CFBundleVersion</key>
    <string>$NETBEANS_VERSION</string>

    <key>CFBundleExecutable</key>
    <string>netbeans</string>

    <key>CFBundlePackageType</key>
    <string>APPL</string>

    <key>CFBundleShortVersionString</key>
    <string>$NETBEANS_VERSION</string>

    <key>CFBundleIdentifier</key>
    <string>org.netbeans.ide.baseide.$NETBEANS_VERSION</string>

    <key>CFBundleSignature</key>
    <string>NETB</string>

    <key>CFBundleInfoDictionaryVersion</key>
    <string>6.0</string>

    <key>CFBundleIconFile</key>
    <string>netbeans.icns</string>

    <key>CFBundleDocumentTypes</key>
    <array>
	<dict>
		<key>CFBundleTypeName</key>
		<string>public.shell-script</string>
		<key>CFBundleTypeRole</key>
		<string>Editor</string>
		<key>LSItemContentTypes</key>
		<array>
			<string>public.shell-script</string>
		</array>
	</dict>
    </array>
    
    <key>NSHighResolutionCapable</key>
    <true/>

    <key>NSSupportsAutomaticGraphicsSwitching</key>
    <true/>
    
  </dict>
</plist>
__EOIP__

# Now unzip the NetBeans zip file into the macOS location and create the Mac application
# that can be launched via Launchpad.
#
unzip $1 -d "$NETBEANS_APPLICATION_LOCATION/Resources/"
mv "$NETBEANS_APPLICATION_LOCATION/Resources/netbeans" "$NETBEANS_APPLICATION_LOCATION/Resources/NetBeans"
cd "$NETBEANS_APPLICATION_LOCATION/MacOS"
ln -s ../Resources/NetBeans/bin/netbeans netbeans
cp "$NETBEANS_APPLICATION_LOCATION/Resources/NetBeans/nb/netbeans.icns" "$NETBEANS_APPLICATION_LOCATION/Resources/"
