package org.example;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class WindowHandles {
    private WebDriver driver;

    public void switchTab() {
        String currentWindowHandle = driver.getWindowHandle(); // get current handle

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles()); // retrieve all handles

        String handleToSwitch = windowHandles.stream()
                .filter(handle -> !handle.equals(currentWindowHandle))
                .findFirst().orElseThrow(IllegalArgumentException::new); // here we are filtering elements which are not equal to our current window handle and get first (since there are only two tabs by requirements)

        driver.switchTo().window(handleToSwitch); // switched to another tab
    }
}
