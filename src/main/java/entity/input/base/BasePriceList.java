package entity.input.base;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter @Getter
public class BasePriceList {
    private ArrayList<Bundle> bundles = new ArrayList<Bundle>();
    private String format;
    private String formatCode;

    public void addBundle(Bundle bundle){
        bundles.add(bundle);
    }
}
