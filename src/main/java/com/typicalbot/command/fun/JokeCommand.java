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
package com.typicalbot.command.fun;

import com.typicalbot.command.Command;
import com.typicalbot.command.CommandArgument;
import com.typicalbot.command.CommandCategory;
import com.typicalbot.command.CommandConfiguration;
import com.typicalbot.command.CommandContext;
import com.typicalbot.command.CommandPermission;

import java.util.Random;

@CommandConfiguration(category = CommandCategory.FUN, aliases = "joke")
public class JokeCommand implements Command {
    // TODO(nsylke): Move these to a file (in resources) or make an API.
    private String[] responses = new String[]{
        "When the window fell into the incinerator, it was a pane in the ash to retrieve.",
        "What's a pirate's favorite letter? It be the Sea",
        "How do you count cows? A 'Cow'culator",
        "Did you hear about the guy whose whole left side was cut off? He's all right now.",
        "My friend's bakery burned down last night. Now his business is toast.",
        "Two peanuts were walking down the street. One was a salted.",
        "My son asked me to stop singing Oasis songs in public. I said maybe.",
        "I used to be a banker but I lost interest",
        "Why did the golfer bring two pairs of pants? In case he got a hole-in-one.",
        "To the man in the wheelchair that stole my camouflage jacket; You can hide, but you can't run.",
        "When my wife told me to stop impersonating a flamingo, I had to put my foot down.",
        "The rotation of earth really makes my day.",
        "Why do chicken coops only have two doors? Because if they had four, they would be chicken sedans.",
        "How do you find Will Smith in the snow? You look for the fresh prints.",
        "A proton, an electron, & a neutron walk into a bar. The proton orders a shot, drinks it, pulls out his wallet, and pays the bartender. The electron orders a shot, drinks it, pulls out his wallet, and pays the bartender. The neutron orders a shot, drinks it, then pulls out his wallet. The bartender stops him and says, \"for you, no charge...\"",
        "Two atoms are walking down the street. One says, \"Oh no! I lost an electron!\", The other asks him, \"Are you sure?\", The first one says, \"Yeah, I'm positive\"",
        "A ham sandwich walks into a bar and orders a beer. The bartender looks at him and says, ‘Sorry we don’t serve food here.",
        "What is Beethoven’s favorite fruit? A ba-na-na-na.",
        "I had a dream that I was a muffler last night. I woke up exhausted.",
        "Two guys walk into a bar, the third one ducks.",
        "What time did the man go to the dentist? Tooth hurt-y.",
        "Found out I was colour blind the other day... That one came right out the purple.",
        "Where are average things built? In the satisfactory.",
        "Yesterday a clown held a door open for me. I thought it was a nice jester.",
        "Just read a few facts about frogs. They were ribbiting.",
        "I just swapped our bed for a trampoline. My wife hit the roof.",
        "Astronomers got tired of watching the moon go round the earth for 24 hours, so they decided to call it a day.",
        "I asked the checkout girl for a date. She said \"They're in the fruit aisle next to the bananas.\"",
        "What's the difference between a well dressed man on a a bicycle and a poorly dressed man on a tricycle? Attire!",
        "What did the pirate say on his 80th birthday? Aye matey",
        "What do you call a sketchy Italian neighbourhood? The Spaghetto.",
        "I have kleptomania, but when it gets bad, I take something for it.",
        "Doorbells, don't knock 'em.",
        "My wife is on a tropical food diet, the house is full of the stuff. It's enough to make a mango crazy.",
        "Whiteboards are remarkable.",
        "Why do so many people with laser hair want to get it removed?",
        "What has twenty legs and flies? Ten pairs of pants.",
        "What has twenty legs and flies? Five dead Horses.",
        "What do you call a fish with no eyes? Fsh.",
        "What do you get if you cross the Atlantic with the Titanic? About halfway.",
        "Why do bakers work so hard? They knead the dough.",
        "What do you call a man with a rug on his head? Matt.",
        "Why shouldn't you play cards in the jungle? Too many cheetahs.",
        "What's big, red, and eats rocks? A big, red, rock-eater.",
        "How do you make toast in the jungle? Pop your bread under a g'rilla.",
        "What lies on the ocean floor and shivers? A nervous wreck.",
        "Who is the quickest draw in the ocean? Billy the Squid.",
        "What's the difference between a jeweller and a prison warden? One sells watches, and the other watches cells.",
        "What's brown and sticky? A stick.",
        "Why did the banana go to the doctors'? He wasn't peeling very well.",
        "Where did Napoleon keep his armies? Up his sleevies.",
        "Where are the Andes? At the end of your armies.",
        "What do you get when you run over a bird with your lawnmower? Shredded tweet.",
        "What do you get if you drop a piano down a coal shaft? A flat minor.",
        "How does a burglar get into your house? Intruder window.",
        "What's worse than finding a worm in your apple? Finding half a worm.",
        "What is the richest country in the world? Ireland. Its capital is always Dublin.",
        "What do you call a sheep with no legs? A cloud.",
        "What was Beethoven's fifth favorite fruit? Ba-na-na-na.",
        "What did the green grape say to the purple grape? Breathe, you fool, breathe!",
        "How do you turn a duck into a soul singer? Put it in a microwave until its bill withers.",
        "Last night me and my girlfriend watched three movies back to back. Luckily I was the one facing the TV.",
        "I've deleted the phone numbers of all the Germans I know from my mobile phone. Now it's Hans free.",
        "Why did the octopus beat the shark in a fight? Because it was well armed.",
        "A red and a blue ship have just collided in the Caribbean. Apparently the survivors are marooned.",
        "What's the advantage of living in Switzerland? Well, the flag is a big plus.",
        "I am terrified of elevators. I'm going to start taking steps to avoid them.",
        "I dreamed about drowning in an ocean made out of orange soda last night. It took me a while to work out it was just a Fanta sea.",
        "My cat just threw up on the carpet. I don't think it's feline well.",
        "I went to the doctor today and he told me I had type A blood, but it was a type O.",
        "Today a girl said she recognized me from vegetarian club, but I'm sure I've never met herbivore.",
        "Why do crabs never give to charity? Because they're shellfish.",
        "People keep making apocalypse jokes like there's no tomorrow.",
        "If you're struggling to think of what to get someone for Christmas. Get them a fridge, and watch their face light up when they open it.",
        "I was thinking about moving to Moscow but there is no point Russian into things.",
        "Did you hear about the new restaurant on the moon? The food is great, but there's just no atmosphere.",
        "Milk is also the fastest liquid on earth. It's pasteurized before you even see it.",
        "Did you hear that the police have a warrant out on a midget psychic ripping people off? It reads \"Small medium at large.\"",
        "A steak pun is a rare medium well done.",
        "Singing in the shower is all fun and games until you get shampoo in your mouth. Then it's a soap opera.",
        "Why can't you hear a pterodactyl using the bathroom? Because the P is silent.",
        "The price of bouncy castles have doubled this year. That's inflation for you.",
        "Whenever I want to start eating healthy, a chocolate bar looks at me and Snickers.",
        "What do you get hanging off banana trees? Sore arms.",
        "I hate perforated lines, they're tearable.",
        "What do you call a fat psychic? A four-chin teller.",
        "A Mexican magician says he'll disappear on the count of 3; \"Uno... dos... poof...\". He disappeared without a tres.",
        "What's the difference between a hippo and a Zippo? One is heavy, and the other is a little lighter.",
        "Why are skeletons so calm? Because nothing gets under their skin!",
        "Why can't skeletons play church music? Because they have no organs.",
        "What does a skeleton order at a restaurant? SPARERIBS",
        "My favorite instrument? the TromBONE, of course.",
        "My brother truly is a numbSKULL",
        "What do skeletons hate the most about wind? Nothing, it goes right through them.",
        "But first, let me take a Skelfie",
        "Why don't skeletons fight each other? They don't have the guts",
        "When does a skeleton laugh? When someone tickles his funny bone!",
        "The skeleton doesn't like my hotdogs. Probably because he doesn't have the stomach for it!",
        "Why are graveyards so noisy? Because of all the coffin!",
        "How did I know where you would go next? Oh I felt it in my bones!",
        "What? A telephone? Nah, I'm using a telebone.",
        "These aren't all of them. I've got a skeleTON more!",
        "The skeleton stood by the fire for too long. Now he's BONE-dry! What a bonehead....",
        "I'm not fat. I'm just big boned!",
        "I'm so lazy, I'm bone idle!",
        "What do skeletons say before they begin dining? Bone-Appetit!",
        "That annoying dog came back and stole more of our bones. He even ran off with his left leg! You could say he didn't leave him with a leg to stand on!",
        "What do you call a skeleton snake? A rattler!",
        "Have you seen my brother? I have a BONE to pick with him.",
        "What did the skeleton say while riding his Harley Davidson motorcycle? I'm bone to be wild!",
        "My brother always works himself down to the bone!",
        "Why did the skeleton want a friend? Because she was feeling bonely",
        "What does a skeleton tile his roof with? Shin-gles!",
        "What do you do if you see a skeleton running across a road? Jump out of your skin and join him!",
        "Skulls are always single because they have no body.",
        "Man, these jokes aren't even that humerus.",
        "These jokes are very bare bones",
        "Why do skeletons makes bad miners? Because they only go 6 FOOT UNDER GROUND",
        "Why does skeletons never go to swimming pools? Because they hate being SOAKED TO THE BONE",
        "You wanna know why skeletons are terrible liars? Everyone can see right through them!",
        "Did ya hear the one about the baby skeleton? He liked to rattle his bones.",
        "A dog stole a skeleton's left leg and left arm the other day, but it's cool he's ALL RIGHT now!",
        "Why do Java Developers always wear glasses? Because they don't C#.",
        "Why is your boss like a demon? Because they are always ready to fire you!",
        "What do you call a lost wolf? A werewolf!",
        "Do you like my drawing of Satan? That's because it's the Greatest Of All Time.",
        "If you play a Windows Installation CD backwards it plays a satanic message, but the most frightening thing of all, if you play it forwards it installs Windows!",
        "What is a devils picket line called? A demonstration!",
        "That song from Frozen is really getting old. Why don't people just Let It Go?",
        "Yesterday I swallowed food coloring. I'm okay. But I feel like I dyed inside!",
        "How does the NASA organize a party? They planet!",
        "What do you call a frozen dog? A pupsicle!",
        "I don't ever trust stairs. I think they're up to something.",
        "Why don't Go developers have patience?  They haven't needed it in a `while`!",
        "Why do Go developers use loops for everything? They love them, and couldn't `contain` themselves!",
        "How do you comfort a JavaScript bug? -- you console it.",
        "What did the suicidal function say? -- goodbye world!",
        "Why couldn't the variable enter the JavaScript store? -- it was (Closured())!",
        "Why did the web designer drown? -- because she didn't know if she had to float:left or float:right. Also, she had too many anchors.",
        "How do you feel like when there is no coffee? -- Depresso!",
        "Why was the Egyptian boy confused? -- His daddy was really a mummy."
    };

    @Override
    public String[] usage() {
        return new String[]{
            "joke"
        };
    }

    @Override
    public String description() {
        return "Returns a joke.";
    }

    @Override
    public CommandPermission permission() {
        return CommandPermission.GUILD_MEMBER;
    }

    @Override
    public void execute(CommandContext context, CommandArgument argument) {
        context.sendMessage(responses[new Random().nextInt(responses.length)]);
    }
}
