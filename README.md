## This is Readme for a KIVORK test task

1. StaleElementReferenceException handling can be found [here](src/main/java/org/example/HomePage.java)
   - The refresh approach (dummy) - we simply refresh the page after exception and try again (not recommended)
   - The wait approach (more intelligent) - here we are waiting for some condition for the specific element
   and after getting element by locator trying to click on it - this approach can be used, but it needs some code adjustments
   - The AjaxElementLocatorFactory approach (preferable -> implicit wait for each element) - here we are using Selenium Page Factory,
   and we can instantiate any page using AjaxElementLocatorFactory which is using SlowLoadingElement and simply pooling the element from DOM 
   if needed - so this approach is preferable because of simplicity.
2. GeoIpTest can be found [here](src/test/java/org/example/test/FreeGeoIpTest.java)
3. Selenium switch to second tab can be found [here](src/main/java/org/example/WindowHandles.java)
   - Here I use stream to filter another tab to switch and throw an exception when there is no such item in list
4. Wait for Page to unload can be found [here](src/main/java/org/example/HomePage.java)
   - Here the idea is to have a marker element and use `ExpectedConditions.invisibilityOfElementLocated()` which will handle Both - StaleElement and NoSuchElement exceptions.