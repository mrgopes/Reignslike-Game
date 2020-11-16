import com.google.gson.Gson;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static Main instance;
    private static String stats;
    private static Gson gson = new Gson();
    private static savenread.Stats stat;
    private static savenread.Highscore score;
    private static int newscore;
    private static String name;
    private static boolean blnInfection;
    private static int infTimeOut;
    private static boolean nextToDie;
    private static boolean waldoTheLoosetounge;
    private static boolean generalCroomwell;
    private static boolean fatherQuill;
    private static boolean princessMaisha;

    static {
        stat = new savenread.Stats();
        stat.setPeople(50);
        stat.setMoney(50);
        stat.setArmy(50);
        stat.setReligion(50);

        infTimeOut = 0;
        score = new savenread.Highscore();
        score.getScore();
        score.getName();

        // Save data to file
        savenread.crunchifyWriteToFile(gson.toJson(stat));
    }
    public static void main(String[] args) {

        savenread.ii = new savenread();
        blnInfection = false;
        instance = new Main();
        nextToDie = false;
        waldoTheLoosetounge = true;

        Scanner input = new Scanner(System.in);

        stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
        System.out.println("What's your name?");
        name = input.next();
        System.out.println("Alright, "+name);
        System.out.println("You are about to start a new game. Are you sure you want to do that?");
        System.out.println("Enter y to continue");
        System.out.println("Enter n to close the game");
        if (!input.next().equalsIgnoreCase("y")) {
            System.exit(0);
            return;
        } else {
            System.out.println(Constants.begin[ ThreadLocalRandom.current().nextInt(0, Constants.begin.length) ]);
            if (!input.next().equalsIgnoreCase("y")) {
                System.exit(0);
            } else {
                System.out.println("Are you sure?");
                if (!input.next().equalsIgnoreCase("y")) {
                    System.exit(0);
                } else {
                    System.out.println("You can always say yes(y) or no(n). This will affect your stats.");
                    System.out.println("Your stats:");
                    System.out.println(stats);
                    try {
                        System.out.println("Highscore: " + savenread.getHighname() + " - " + savenread.getHighsc());
                    } catch (Exception ex){
                        System.out.println("No recet highscores");
                    }
                    System.out.println("Do you understand?");
                    if (!input.next().equalsIgnoreCase("y")) {
                        System.exit(0);
                    } else {
                        newscore = 0;
                        do {
                            if (blnInfection=false) {
                                int change = (int) (Math.random() * 25 + 1);
                                instance.random(change);
                                newscore = newscore + 1;
                            } else {
                                if (infTimeOut > 0) {
                                    int change = (int) (Math.random() * 15 + 1);
                                    instance.randomInfected(change);
                                    newscore = newscore + 1;
                                    infTimeOut = infTimeOut - 1;
                                } else {
                                    blnInfection = false;
                                    int change = (int) (Math.random() * 25 + 1);
                                    instance.random(change);
                                    newscore = newscore + 1;
                                }
                            }
                        }
                        while (((stat.getMoney() > 10) && (stat.getMoney() < 90)) && (((stat.getPeople() > 10) && (stat.getPeople() < 90))) && (((stat.getArmy() > 10)) && (stat.getArmy() < 90))
                                && ((stat.getReligion() > 10) && (stat.getReligion() < 90)));
                        if (stat.getReligion() > 90) {
                            System.out.println("The Pope's armies are taking control of our Holy country. This is Heaven on Earth!");
                            input.next();
                            System.out.println("The Church decided you would make a great martyr. They tore you limb from limb.");
                        }
                        if (stat.getReligion() < 10) {
                            System.out.println("A pagan mob murdered you during your escape attempt.");
                        }
                        if (stat.getMoney() > 90) {
                            System.out.println("Your contry is so rich! We're throwing a party to celebrate with the most expensive food money can buy!");
                            input.next();
                            System.out.println("You eat so much that you end up choking. Everyone is drunk so your death is only noticed by the following morning.");
                        }
                        if (stat.getMoney() < 10) {
                            System.out.println("Your country is ruined my Lord. Merchants and nobility owns everything.");
                            System.out.println("The new oligarchy forces you to exile.");
                        }
                        if (stat.getArmy() > 90) {
                            System.out.println("The army locks you up in the tallest tower of the castle where you will decay to rags and bones.");
                        }
                        if (stat.getArmy() < 10) {
                            System.out.println("You lead the last remnants of your army and die on the steps of the throne.");
                        }
                        if (stat.getPeople() < 10) {
                            System.out.println("Your people are famished and unrest is spreading.");
                            input.next();
                            System.out.println("The castle is ransacked, your court disperses and you're left with pigeons to rule over.");
                        }
                        if (stat.getPeople() > 90) {
                            System.out.println("It's anarchy in the streets my lord! A large mob is breaking into the castle!");
                            input.next();
                            System.out.println("Your reign ends there but no one harms you. You die many years later praised and well remembered.");
                        }
                        System.out.println("You lost!");
                        System.out.println(newscore+" months in power | Highscore: "+savenread.getHighname()+" - "+savenread.getHighsc());
                        if (newscore > savenread.getHighsc()) {
                            score.setName(name);
                            score.setScore(newscore);
                            System.out.println("Highscore updated!");
                            savenread.Score(gson.toJson(score));
                        }
                        System.exit(0);
                    }
                }
            }
        }

    }

    private void randomInfected(int change) {
        Scanner answer = new Scanner(System.in);
        Random r = new Random();
        int number = r.nextInt(5) + 1;
        switch (number) {
            //If positive answer, -money +religion
            case 1:
                System.out.println(Constants.mmoneypreligioninf[ ThreadLocalRandom.current().nextInt(0, Constants.mmoneypreligioninf.length) ]);
                if (!answer.next().equalsIgnoreCase("n")) {
                    stat.setMoney(stat.getMoney()-change);
                    stat.setReligion(stat.getReligion()+change);
                } else {
                    stat.setMoney(stat.getMoney()+change);
                    stat.setReligion(stat.getReligion()-change);
                }
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                return;
            //If positive answer, -money +people
            case 2:
                System.out.println(Constants.ppeoplemmoneyinf[ ThreadLocalRandom.current().nextInt(0, Constants.ppeoplemmoneyinf.length) ]);
                if (!answer.next().equalsIgnoreCase("n")) {
                    stat.setMoney(stat.getMoney()-change);
                    stat.setPeople(stat.getPeople()+change);
                } else {
                    stat.setMoney(stat.getMoney()+change);
                    stat.setPeople(stat.getPeople()-change);
                }
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                return;

            //If positive anmswer, -army +people
            case 3:
                System.out.println(Constants.marmyppeopleinf[ ThreadLocalRandom.current().nextInt(0, Constants.marmyppeopleinf.length) ]);
                if (!answer.next().equalsIgnoreCase("n")) {
                    stat.setArmy(stat.getArmy()-change);
                    stat.setPeople(stat.getPeople()+change);
                } else {
                    stat.setArmy(stat.getArmy()+change);
                    stat.setPeople(stat.getPeople()-change);
                }
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                return;
            //If positive, +people -religion
            case 4:
                System.out.println(Constants.ppeoplemreligioninf[ ThreadLocalRandom.current().nextInt(0, Constants.ppeoplemreligioninf.length) ]);
                if (!answer.next().equalsIgnoreCase("n")) {
                    stat.setReligion(stat.getReligion()-change);
                    stat.setPeople(stat.getPeople()+change);
                } else {
                    stat.setReligion(stat.getReligion()+change);
                    stat.setPeople(stat.getPeople()-change);
                }
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                return;
            //If positive, +army -religion
            case 5:
                System.out.println(Constants.parmymreligioninf[ ThreadLocalRandom.current().nextInt(0, Constants.parmymreligioninf.length) ]);
                if (!answer.next().equalsIgnoreCase("n")) {
                    stat.setReligion(stat.getReligion()-change);
                    stat.setArmy(stat.getArmy()+change);
                } else {
                    stat.setReligion(stat.getReligion()+change);
                    stat.setArmy(stat.getArmy()-change);
                }
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                return;
        }
    }

    private void random(int change) {
        Scanner answer = new Scanner(System.in);
        Random r = new Random();
        int number = r.nextInt(11)+1;
        switch (number) {
            case 1:
                getRandomTask(change);
                return;
            case 2:
                getRandomTask(change);
                return;
            case 3:
                getRandomTask(change);
                return;
            case 4:
                getRandomTask(change);
                return;
            case 5:
                getRandomTask(change);
                return;
            case 6:
                getRandomTask(change);
                return;
            case 7:
                getRandomTask(change);
                return;
            case 8:
                getRandomTask(change);
                return;
            case 9:
                Satan();
                return;
            case 10:
                Attack();
                return;
            case 11:
                Infection();
        }
    }

    private void Infection() {
        Scanner answer = new Scanner(System.in);
        if (newscore >= 24) {
            System.out.println("You woke up in the middle of a night and weird stuff started to happen.");
            blnInfection=true;
            infTimeOut=1000;
        } else {
            System.out.println("I've found a blue mushroom sir. Would you like to eat it?");
            if (answer.next().equalsIgnoreCase("y")) {
                System.out.println("You ate the blue mushroom and then weird stuff started to happen.");
                blnInfection=true;
                infTimeOut=10;
            } else {
                System.out.println("Alright then, but I am sure it's very delicious!");
            }
        }
    }

    private void getRandomTask(int change) {
        Scanner answer = new Scanner(System.in);
        Random r = new Random();
        int number = r.nextInt(5) + 1;
        switch (number) {
            //If positive answer, -money +religion
            case 1:
                System.out.println(Constants.mmoneypreligion[ ThreadLocalRandom.current().nextInt(0, Constants.mmoneypreligion.length) ]);
                if (!answer.next().equalsIgnoreCase("n")) {
                    stat.setMoney(stat.getMoney()-change);
                    stat.setReligion(stat.getReligion()+change);
                } else {
                    stat.setMoney(stat.getMoney()+change);
                    stat.setReligion(stat.getReligion()-change);
                }
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                return;
            //If positive answer, -money +people
            case 2:
                System.out.println(Constants.ppeoplemmoney[ ThreadLocalRandom.current().nextInt(0, Constants.ppeoplemmoney.length) ]);
                if (!answer.next().equalsIgnoreCase("n")) {
                    stat.setMoney(stat.getMoney()-change);
                    stat.setPeople(stat.getPeople()+change);
                } else {
                    stat.setMoney(stat.getMoney()+change);
                    stat.setPeople(stat.getPeople()-change);
                }
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                return;

            //If positive anmswer, -army +people
            case 3:
                System.out.println(Constants.marmyppeople[ ThreadLocalRandom.current().nextInt(0, Constants.marmyppeople.length) ]);
                if (!answer.next().equalsIgnoreCase("n")) {
                    stat.setArmy(stat.getArmy()-change);
                    stat.setPeople(stat.getPeople()+change);
                } else {
                    stat.setArmy(stat.getArmy()+change);
                    stat.setPeople(stat.getPeople()-change);
                }
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                return;
            //If positive, +people -religion
            case 4:
                System.out.println(Constants.ppeoplemreligion[ ThreadLocalRandom.current().nextInt(0, Constants.ppeoplemreligion.length) ]);
                if (!answer.next().equalsIgnoreCase("n")) {
                    stat.setReligion(stat.getReligion()-change);
                    stat.setPeople(stat.getPeople()+change);
                } else {
                    stat.setReligion(stat.getReligion()+change);
                    stat.setPeople(stat.getPeople()-change);
                }
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                return;
            //If positive, +army -religion
            case 5:
                System.out.println(Constants.parmymreligion[ ThreadLocalRandom.current().nextInt(0, Constants.parmymreligion.length) ]);
                if (!answer.next().equalsIgnoreCase("n")) {
                    stat.setReligion(stat.getReligion()-change);
                    stat.setArmy(stat.getArmy()+change);
                } else {
                    stat.setReligion(stat.getReligion()+change);
                    stat.setArmy(stat.getArmy()-change);
                }
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                return;
        }
    }

    public void Attack() {
        Scanner answer = new Scanner(System.in);
        System.out.println(Constants.attack[ ThreadLocalRandom.current().nextInt(0, Constants.attack.length) ]);
        if (answer.next().equalsIgnoreCase("y")) {
            stat.setArmy(stat.getArmy() - 15);
            stat.setReligion(stat.getReligion() - 15);
            stat.setMoney(stat.getMoney() - 15);
            stat.setPeople(stat.getPeople() + 15);
            stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
            System.out.println(stats);
        } else {
            System.out.println("Do you want to save the treasury?");
            if (answer.next().equalsIgnoreCase("y")) {
                stat.setMoney(stat.getMoney() + 15);
                stat.setReligion(stat.getReligion() - 15);
                stat.setPeople(stat.getPeople() - 15);
                stat.setArmy(stat.getArmy() - 15);
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
            } else {
                System.out.println("Do you want to close the gates to the capital?");
                if (answer.next().equalsIgnoreCase("y")) {
                    stat.setMoney(stat.getMoney() - 15);
                    stat.setReligion(stat.getReligion() - 15);
                    stat.setPeople(stat.getPeople() - 15);
                    stat.setArmy(stat.getArmy() + 15);
                    stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                    System.out.println(stats);
                }
                else {
                    System.out.println("Do you want to save the churches?");
                    if (answer.next().equalsIgnoreCase("y")) {
                        stat.setMoney(stat.getMoney() - 15);
                        stat.setReligion(stat.getReligion() + 15);
                        stat.setPeople(stat.getPeople() - 15);
                        stat.setArmy(stat.getArmy() - 15);
                        stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                        System.out.println(stats);
                    } else {
                        stat.setMoney(stat.getMoney() - 15);
                        stat.setReligion(stat.getReligion() - 15);
                        stat.setPeople(stat.getPeople() - 15);
                        stat.setArmy(stat.getArmy() - 15);
                        stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                        System.out.println(stats);
                        return;
                    }
                }
            }
        }
    }

    public void Satan() {
        System.out.println("Woof woof! (Do you want to follow the dog?)");
        Scanner answer = new Scanner(System.in);
        if (answer.next().equalsIgnoreCase("y")) {
            System.out.println("Wrr! Woof woof! (The dog walked you to a caravan. Do you want to enter it?)");
            if (answer.next().equalsIgnoreCase("y")) {
                getRandomSatan();
            } else {
                System.out.println("Rawr! (The dog slowly fades away)");
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
            }
        } else {
            stat.setMoney(stat.getMoney() - 5);
            stat.setReligion(stat.getReligion() - 5);
            stat.setPeople(stat.getPeople() - 5);
            stat.setArmy(stat.getArmy() - 5);
            stats = "People: " + stat.getPeople() + " | Money: " + stat.getMoney() + " | Army: " + stat.getArmy() + " | Religion: " + stat.getReligion();
            System.out.println(stats);
            System.out.println("Wrr! (The dog slowly fades away)");
        }
    }

    public void getRandomSatan() {
        Scanner answer = new Scanner(System.in);
        Random r = new Random();
        int number = r.nextInt(2) + 1;
        switch (number) {
            case 1:
                System.out.println("I see... (A king?)");
                if (answer.next().equalsIgnoreCase("y")) {
                    System.out.println("In front of him lays... (A dog?)");
                    if (answer.next().equalsIgnoreCase("y")) {
                        System.out.println("That will be 600 gold, sir.");
                        if (answer.next().equalsIgnoreCase("y")) {
                            stat.setMoney(stat.getMoney() - 15);
                            stat.setReligion(stat.getReligion() - 25);
                            stat.setPeople(stat.getPeople() + 10);
                            stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                            System.out.println(stats);
                            }
                        else {
                            stat.setMoney(stat.getMoney() + 5);
                            stat.setReligion(stat.getReligion() + 5);
                            stat.setPeople(stat.getPeople() - 10);
                            stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                            System.out.println(stats);
                        }
                    } else {
                        System.out.println("A ruin. That will be 600 gold, sir.");
                        if (answer.next().equalsIgnoreCase("y")) {
                            stat.setMoney(stat.getMoney() - 15);
                            stat.setReligion(stat.getReligion() - 25);
                            stat.setPeople(stat.getPeople() + 10);
                            stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                            System.out.println(stats);

                        }
                        else {
                            stat.setMoney(stat.getMoney() + 5);
                            stat.setReligion(stat.getReligion() + 5);
                            stat.setPeople(stat.getPeople() - 10);
                            stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                            System.out.println(stats);
                        }
                    }
                } else {
                    System.out.println("A nobleman; in front of him lays.. (A ruin?)");
                    if (answer.next().equalsIgnoreCase("y")) {
                        System.out.println("That will be 600 gold, sir.");
                        if (answer.next().equalsIgnoreCase("y")) {
                            stat.setMoney(stat.getMoney() - 15);
                            stat.setReligion(stat.getReligion() - 25);
                            stat.setPeople(stat.getPeople() + 10);
                            stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                            System.out.println(stats);
                            }
                        else {
                            stat.setMoney(stat.getMoney() + 5);
                            stat.setReligion(stat.getReligion() + 5);
                            stat.setPeople(stat.getPeople() - 10);
                            stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                            System.out.println(stats);
                        }
                    } else {
                        System.out.println("A dog. That will be 600 gold, sir.");
                        if (answer.next().equalsIgnoreCase("y")) {
                            stat.setMoney(stat.getMoney() - 15);
                            stat.setReligion(stat.getReligion() - 25);
                            stat.setPeople(stat.getPeople() + 10);
                            stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                            System.out.println(stats);
                            }
                        else {
                            stat.setMoney(stat.getMoney() + 5);
                            stat.setReligion(stat.getReligion() + 5);
                            stat.setPeople(stat.getPeople() - 10);
                            stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                            System.out.println(stats);
                        }
                    }
                }
                return;
            case 2:
                System.out.println("Hello, sir. Do you want me to teach you some white magic?");
                if (answer.next().equalsIgnoreCase("y")) {
                    System.out.println("Do you want me to teach you how to treat the devil?");
                    if (answer.next().equalsIgnoreCase("y")) {
                        System.out.println("I see numbers, 1584, 5698, 6554, 1223... (The witch slowly fades away and you suddenly wake up in your bed)");
                    } else {
                        System.out.println("Do you want me to teach you how to brew the elixir of overpower?");
                        if (answer.next().equalsIgnoreCase("y")) {
                            System.out.println("Woosh, do you want to use it?");
                            if (answer.next().equalsIgnoreCase("y")) {
                                stat.setMoney(100);
                                stat.setReligion(100);
                                stat.setPeople(100);
                                stat.setArmy(100);
                                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                                System.out.println(stats);
                                System.out.println("Your kingdom became an anarchy!");
                                System.out.println("You lost!");
                                System.out.println(newscore+" months in power | Highscore: "+savenread.getHighname()+" - "+savenread.getHighsc());
                                if (newscore > savenread.getHighsc()) {
                                    score.setName(name);
                                    score.setScore(newscore);
                                    System.out.println("Highscore updated!");
                                    savenread.Score(gson.toJson(score));
                                }
                                System.exit(0);
                            } else {
                                System.out.println("(The witch smiles but it's a little bit forced)");
                                System.out.println("That will be 1000 gold.");
                                    if (answer.next().equalsIgnoreCase("y")) {
                                        stat.setMoney(stat.getMoney() - 15);
                                        stat.setReligion(stat.getReligion() - 25);
                                        stat.setPeople(stat.getPeople() + 10);
                                        stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                                        System.out.println(stats);

                                    }
                                    else {
                                        stat.setMoney(stat.getMoney() + 5);
                                        stat.setReligion(stat.getReligion() + 5);
                                        stat.setPeople(stat.getPeople() - 10);
                                        stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                                        System.out.println(stats);
                                    }
                                }
                        }
                    }
                } else {
                    System.out.println("Do you want me to tell you your future then?");
                    if (answer.next().equalsIgnoreCase("y")) {
                        System.out.println("Your third decision will cause a catastrophe... (The witch suddenly disappeard and you woke up in your bed)");
                        stat.setPeople(stat.getPeople() - 10);
                        stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                        System.out.println(stats);
                        int change = (int) (Math.random()*25+1);
                        instance.getRandomTask(change);
                        newscore = newscore+1;
                        instance.getRandomTask(change);
                        newscore = newscore+1;
                        instance.getRandomTask(change);
                        newscore = newscore+1;
                        instance.Attack();
                        newscore = newscore+1;
                    } else {
                        System.out.println("Why are you wasting my time then?...");
                        stat.setPeople(stat.getPeople() - 10);
                        stat.setReligion(stat.getReligion() + 15);
                        stat.setArmy(stat.getArmy() - 10);
                        stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                        System.out.println(stats);
                    }
                }
                return;
            case 3:
                System.out.println("Rex moans in despair. Something is not right");
                answer.next();
                System.out.println("Time is gone, space is insane. Here it comes, here again. You feel your mouth closing down as if you never had one.");
                answer.next();
                System.out.println("Little King, little puppet.");
                answer.next();
                System.out.println("No words.");
                answer.next();
                System.out.println("Few thoughts.");
                answer.next();
                System.out.println("Many deaths.");
                answer.next();
                System.out.println("Centuries ago you asked for eternal power in exchange for your soul...");
                answer.next();
                System.out.println("Fortunately you didn't mention anything about immortality.");
                answer.next();
                System.out.println("Each of your deaths is enjoyable.");
                stats = "People: 100 | Money: 100 | Army: 100 | Religion: 100";
                System.out.println(stats);
                answer.next();
                System.out.println("But even more so is the choice I will let you make now.");
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
                answer.next();
                System.out.println("Go little puppet, and see you again in 666 years.");
                answer.next();
                stat.setArmy(stat.getArmy()+25);
                stat.setPeople(stat.getPeople()+25);
                stat.setReligion(stat.getReligion()+25);
                stat.setMoney(stat.getMoney()+25);
                stats = "People: "+stat.getPeople()+" | Money: "+stat.getMoney()+" | Army: "+stat.getArmy()+" | Religion: "+stat.getReligion();
                System.out.println(stats);
        }
    }


}
