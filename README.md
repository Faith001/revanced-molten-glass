# revanced-molten-glass

A simple [ReVanced](https://github.com/revanced) patch that enables your favorite application to trust user-supplied CAs.

## Pre-requisites
- An `OpenJDK17` installation

## Installation
1. Grab the latest release of your favorite application from APKMirror  
(will likely be in the form of an `apkm` bundle that needs to merged, if not - skip step 3)

2. Grab the latest releases of [APKEditor](https://github.com/REAndroid/APKEditor), [revanced-cli](https://github.com/ReVanced/revanced-cli) and this patch

3. Merge the `apkm` bundle into a single `apk` via APKEditor:  
`java -jar apkeditor.jar m -i your_app.apkm`

4. Use `revanced-cli` with the molten-glass patch `patches-mg-1.0.0.rvp` on the resulting merged `apk`:  
`java -jar revanced-cli.jar patch -p patches-mg-1.0.0.rvp your_app_merged.apk`

5. If everything finished successfully, you should now have `your_app_merged-patched.apk` which can be installed to your device

## Building
For building, please refer to the [ReVanced documentation](https://github.com/ReVanced/revanced-documentation).
