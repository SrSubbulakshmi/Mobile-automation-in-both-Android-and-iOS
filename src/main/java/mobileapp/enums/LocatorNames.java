package mobileapp.enums;

public enum LocatorNames {
    accessibility("accessibility"),
    iOSClassChain("iOSClassChain"),
    className("className"),
    xpath("xpath"),
    uiAutomator("uiAutomator"),
    iOSNsPredicate("iOSNsPredicate");

    String locatorName;
    LocatorNames(String locatorName) {
        this.locatorName = locatorName;
    }
}
