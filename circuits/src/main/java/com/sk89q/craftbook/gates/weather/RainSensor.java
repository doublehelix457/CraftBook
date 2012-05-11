package com.sk89q.craftbook.gates.weather;


import org.bukkit.Server;
import org.bukkit.block.Sign;
import com.sk89q.craftbook.ic.core.AbstractIC;
import com.sk89q.craftbook.ic.core.AbstractICFactory;
import com.sk89q.craftbook.ic.core.ChipState;
import com.sk89q.craftbook.ic.core.IC;

public class RainSensor extends AbstractIC {

    public RainSensor(Server server, Sign sign) {
        super(server, sign);
    }

    @Override
    public String getTitle() {
        return "Is It Rain";
    }

    @Override
    public String getSignTitle() {
        return "IS IT RAIN";
    }

    @Override
    public void trigger(ChipState chip) {
        if (chip.getInput(0)) {
        	chip.setOutput(0, getSign().getWorld().hasStorm());
        }
    }


    public static class Factory extends AbstractICFactory {
        public Factory(Server server) {
            super(server);
        }

        @Override
        public IC create(Sign sign) {
            return new RainSensor(getServer(), sign);
        }
    }

}
