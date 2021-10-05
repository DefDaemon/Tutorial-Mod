package com.defdaemon.tutorialmod.common.command;

import com.defdaemon.tutorialmod.TutorialMod;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.server.level.ServerPlayer;

import java.util.EnumSet;
import java.util.Set;

public class ReturnHomeCommand
{
    public ReturnHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("home").then(Commands.literal("return").executes((command) -> {
            return returnHome(command.getSource());
        })));
    }

    private int returnHome(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        Set<ClientboundPlayerPositionPacket.RelativeArgument> set = EnumSet.noneOf(ClientboundPlayerPositionPacket.RelativeArgument.class);
        boolean hasHomepos = player.getPersistentData().getIntArray(TutorialMod.MOD_ID + "homepos").length != 0;

        if(hasHomepos) {
            int[] playerPos = player.getPersistentData().getIntArray(TutorialMod.MOD_ID + "homepos");
            set.add(ClientboundPlayerPositionPacket.RelativeArgument.X_ROT);
            set.add(ClientboundPlayerPositionPacket.RelativeArgument.Y_ROT);
            player.connection.teleport(playerPos[0] , playerPos[1], playerPos[2], player.getXRot(), player.getYRot(), set);
            source.sendSuccess(new TranslatableComponent("commands.home.return.success"), true);
            return 1;
        } else {
            source.sendSuccess(new TranslatableComponent("commands.home.return.failure"), true);
            return -1;
        }
    }
}
