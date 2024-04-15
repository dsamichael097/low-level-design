package com.example.lowleveldesign.splitwise.domain.split_type;

import com.example.lowleveldesign.splitwise.domain.User;
import lombok.Getter;
import lombok.Setter;

public class PercentSplit extends Split{
    private double percent;

    public PercentSplit(User user, double percent) {
        super(user);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
