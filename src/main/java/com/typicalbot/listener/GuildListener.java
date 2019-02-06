/**
 * Copyright 2019 Bryan Pikaard & Nicholas Sylke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.typicalbot.listener;

import com.typicalbot.command.Command;
import com.typicalbot.command.CommandArgument;
import com.typicalbot.command.CommandContext;
import com.typicalbot.config.Config;
import com.typicalbot.shard.Shard;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author TypicalBot
 * @since 3.0.0-alpha
 */
public class GuildListener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuildListener.class);

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        LOGGER.info("Joined guild {} with {} users", event.getGuild().getName(), event.getGuild().getMembers().size());
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        LOGGER.info("Left guild {}", event.getGuild().getName());
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // TODO(nsylke): Check to see if we are missing anything... maybe event.getAuthor().isFake() ?!?!
        if (event.getAuthor() == null || event.getAuthor().isBot()) return;
        if (!event.getGuild().isAvailable()) return;
        if (!event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) return;

        String rawMessage = event.getMessage().getContentRaw();
        String prefix = Config.getConfig("discord").getString("prefix");

        if (rawMessage.matches("^<@!?" + event.getJDA().getSelfUser().getId() + ">$")) {
            // TODO(nsylke): Show default prefix from configuration if setting in database is not set.
            event.getChannel().sendMessage("The server's prefix is `" + prefix + "`.").queue();
            return;
        }

        List<String> arguments = new ArrayList<>(Arrays.asList(rawMessage.split("\\s+")));
        String commandName = arguments.get(0);

        if (commandName.startsWith(prefix)) {
            Command command = Shard.getSingleton().getCommandManager().findCommand(commandName.substring(prefix.length()));

            if (command == null) return;

            arguments.remove(0);
            CommandArgument commandArgument = new CommandArgument(arguments);
            CommandContext commandContext = new CommandContext(event.getMessage());

            // TODO(nsylke): Need a backup way for those who haven't given TypicalBot the permission.
            if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS)) {
                try {
                    command.execute(commandContext, commandArgument);
                } catch (UnsupportedOperationException ex) {
                    event.getMessage().getChannel().sendMessage(ex.getMessage()).queue();
                }
            }
        }
    }
}
