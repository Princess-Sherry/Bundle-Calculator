package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * A class for each type of media and its related bundle items information
 */
@Setter
@Getter
@AllArgsConstructor
public class Bundle {
    private String formatCode;
    private List<BundleItem> bundleItems;
}
