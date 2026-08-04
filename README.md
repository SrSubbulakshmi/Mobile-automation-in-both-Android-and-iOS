# Mobile-Automation
## Hybrid model - Can execute on local and on Browser cloud
Hybrid Model - Can execute on local and on BrowserStack cloud

This framework provides a robust and scalable solution for mobile application testing. It is designed to support cross-platform test automation for both Android and iOS applications using Appium. The key feature of this framework is its ability to seamlessly switch between local execution (using simulators or emulators) and cloud execution (using BrowserStack's device cloud) by simply changing a [default.property](src/test/java/mobileapp/tests/resources/default.properties).

## Getting Started
1. Clone the Repository:
   - git clone [your-repository-url]
2. Update Dependencies:
    - ```sh mvn clean install ```
    - Make sure you have all the necessary Maven dependencies as listed in pom.xml. 
   
## Configure your environment
### For Local Execution:
- Go to the data.properties change the variable to `local` like this `execution.environment = local`
- Go to the data.properties change the platform variable to `ios/android` like this `execution.environment = android`
- Create an `apps` folder in the root project and put the courier app `apk and .ipa or .app` into the folder
- In the `default.properties` set up all the information for the emulator
- Make sure your `emulator or simulator` and `appium server` are running on your local
- Run the following maven command `mvn clean test` it will run the tests on your local

### For BrowserStack Cloud Execution:
- The app URL is obtained after uploading your application to BrowserStack.
- Go to the [default.properties](src/test/java/mobileapp/tests/resources/default.properties) change the variable to `browserstack` like this `execution.environment = browserstack`
- Go to the [default.properties](src/test/java/mobileapp/tests/resources/default.properties) change the platform variable to `ios/android` like this `execution.environment = android`

#### Use Browserstack SDK way
- Update the `browserstack.yml` file with your BrowserStack `username, access key, and app URL`.
- Go to the [default.properties](src/test/java/mobileapp/tests/resources/default.properties) change the `browserstack.legacyOrSDK` variable to `SDK` like this `browserstack.legacyOrSDK = SDK`
- Run the following maven command `mvn clean test -Pbrowserstack` it will run the tests on Browser cloud

#### Use Legacy way
- In the [default.properties](src/test/java/mobileapp/tests/resources/default.properties) set up all the following information for what you want to run on Browserstack cloud
- Go to the [default.properties](src/test/java/mobileapp/tests/resources/default.properties) change the `browserstack.legacyOrSDK` variable to `legacy` like this `browserstack.legacyOrSDK = legacy`
- For example if you want to run with `iOS` platform
    - ``` iOS.deviceName=iPhone 14 Pro Max ```
    - ``` iOS.platformVersion=16 ```
    - ``` iOS.platformName=ios ```
    - ``` iOS.geoLocation=CA ```
    - ``` If you have any other extra information like app, username, password etc... ```
- Run the following maven command `mvn clean test` it will run the tests on Browser cloud
