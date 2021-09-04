import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * A class for each type of media and its related bundle items information
 */
@Setter @Getter
@AllArgsConstructor
public class Bundle {
    private String formatCode;
    private ArrayList<BundleItem> bundleItems;
}
