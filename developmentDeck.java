public class developmentDeck {
    private developmentCard[] deck;

    public developmentDeck()
    {
        deck = new developmentCard[25];
        //knights
        for(int i = 0; i<14; i++)
        {
            deck[i] = new developmentCard(1, 0, "knight");
        }
        //progress
        for(int i = 14; i<16; i++)
        {
            deck[i] = new developmentCard(2, 0, "road builder");
        }
        for(int i = 16; i<18; i++)
        {
            deck[i] = new developmentCard(2, 0, "year of plenty");
        }
        for(int i = 18; i<20; i++)
        {
            deck[i] = new developmentCard(2, 0, "monopoly");
        }
        //vps
        deck[20] = new developmentCard(3, 1, "university");
        deck[21] = new developmentCard(3, 1, "library");
        deck[22] = new developmentCard(3, 1, "great hall");
        deck[23] = new developmentCard(3, 1, "chapel");
        deck[24] = new developmentCard(3, 1, "market");

        shuffle();
    }

    //if deck.length = 0 we gotta reshuffle
    //deals the top card (duh)
    public Card dealTop()
    {
        Card top = new Card(deck[deck.length-1]); //top card
        developmentCard[] newDeck = deck[]-deck.length-2; //new deck minus top card
        return top;
    }
    //shuffles
    public void shuffle()
    {
        for(int i = 0; i<deck.length; i++)
        {
            int rand = Math.random() * deck.length + 1; //random card
            Card temp = deck[rand]; //swaps the cards
            deck[rand] = deck[i];
            deck[i] = deck[temp];
        }
    }
}
