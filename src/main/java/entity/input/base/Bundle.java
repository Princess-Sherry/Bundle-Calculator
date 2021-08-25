package entity.input.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Bundle {
    private int amount;
    private double price;
}
