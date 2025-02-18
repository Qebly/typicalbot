/*
 * Copyright 2019 Bryan Pikaard, Nicholas Sylke and the TypicalBot contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.typicalbot.command.interaction;

import com.typicalbot.command.Command;
import com.typicalbot.command.CommandArgument;
import com.typicalbot.command.CommandCategory;
import com.typicalbot.command.CommandConfiguration;
import com.typicalbot.command.CommandContext;
import com.typicalbot.command.CommandPermission;
import net.dv8tion.jda.core.entities.User;

import java.util.Random;

@CommandConfiguration(category = CommandCategory.INTERACTION, aliases = {"shoot", "pew"})
public class ShootCommand implements Command {
    @Override
    public String[] usage() {
        return new String[]{
            "shoot <@mention>",
            "shoot"
        };
    }

    @Override
    public String description() {
        return "'Shoots' the mentioned user.";
    }

    @Override
    public CommandPermission permission() {
        return CommandPermission.GUILD_MEMBER;
    }

    @Override
    public void execute(CommandContext context, CommandArgument argument) {
        User author = context.getMessage().getAuthor();

        String[] options = {"Bam! Headshot.", "Yikes! Missed by a mile.", ""};
        Random rand = new Random();
        int x = rand.nextInt(options.length);

        if (!argument.has()) {
            context.sendMessage("{0} just shot at themselves! :scream: {1}", author.getAsMention(), options[x]);
            return;
        }

        User mention = context.getUser(argument.get(0));

        if (mention.equals(author)) {
            context.sendMessage("{0} just shot at themselves! :scream: {1}", author.getAsMention(), options[x]);
            return;
        } else if (mention == null) {
            context.sendMessage("{0}, the specified user does not exist. Try again.", author.getAsMention());
            return;
        }

        context.sendMessage("{0} just shot at {1}! :scream: {2}", author.getAsMention(), mention.getAsMention(), options[x]);
    }
}
