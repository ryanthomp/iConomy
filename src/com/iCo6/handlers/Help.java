package com.iCo6.handlers;

import java.util.LinkedHashMap;

import org.bukkit.command.CommandSender;

import com.iCo6.iConomy;
import com.iCo6.command.Handler;
import com.iCo6.command.Parser.Argument;
import com.iCo6.command.exceptions.InvalidUsage;
import com.iCo6.util.Messaging;

public class Help extends Handler {

    public Help(iConomy plugin) {
        super(plugin, iConomy.Template);
    }

    @Override
    public boolean perform(CommandSender sender, LinkedHashMap<String, Argument> arguments) throws InvalidUsage {
        if(!hasPermissions(sender, "help")) {
			template.noPermission(sender);
			return false;
        }

        // TODO: add support for checking help on single command.

        Messaging.send(sender, "`w ");
        Messaging.send(sender, "`w iConomy (`yCelty`w)");
        Messaging.send(sender, "`w ");
        Messaging.send(sender, "`S [] `wRequired, `S() `sOptional");
        Messaging.send(sender, "`w ");

        for (String action : plugin.Commands.getHelp().keySet()) {
            if(!hasPermissions(sender, action))
                continue;

            String description = plugin.Commands.getHelp(action)[1];
            String command = "";

            if(action.equalsIgnoreCase("money") || action.equalsIgnoreCase("money+"))
                command = "/money `w" + plugin.Commands.getHelp(action)[0] + "`s";
            else
                command = "/money `w" + action + plugin.Commands.getHelp(action)[0] + "`s";

            command = command.replace("[", "`S[`s").replace("]", "`S]").replace("(", "`S(");
            Messaging.send(sender, String.format("   %1$s `Y-`y %2$s", command, description));
        }

        return false;
    }
}
