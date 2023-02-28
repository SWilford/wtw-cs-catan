import javax.swing.*;

public class developmentDeck {
    private card[] deck;

    public developmentDeck()
    {
        deck = new card[25];
        //knights
        for(int i = 0; i<14; i++)
        {
            deck[i] = new developmentCard(1, 0, "knight");
            deck[i].setImage(new ImageIcon("images/devCards/knight.png"));
        }
        //progress
        for(int i = 14; i<16; i++)
        {
            deck[i] = new developmentCard(2, 0, "road builder");
            deck[i].setImage(new ImageIcon("images/devCards/roadBuilding.png"));
        }
        for(int i = 16; i<18; i++)
        {
            deck[i] = new developmentCard(2, 0, "year of plenty");
            deck[i].setImage(new ImageIcon("images/devCards/yop.png"));
        }
        for(int i = 18; i<20; i++)
        {
            deck[i] = new developmentCard(2, 0, "monopoly");
            deck[i].setImage(new ImageIcon("images/devCards/monopoly.png"));

        }
        //vps
        deck[20] = new developmentCard(3, 1, "university");
        deck[20].setImage(new ImageIcon("images/devCards/university.png"));

        deck[21] = new developmentCard(3, 1, "library");
        deck[21].setImage(new ImageIcon("images/devCards/library.png"));

        deck[22] = new developmentCard(3, 1, "great hall");
        deck[22].setImage(new ImageIcon("images/devCards/govHouse.png"));

        deck[23] = new developmentCard(3, 1, "chapel");
        deck[23].setImage(new ImageIcon("images/devCards/chapel.png"));

        deck[24] = new developmentCard(3, 1, "market");
        deck[24].setImage(new ImageIcon("images/devCards/market.png"));


        shuffle();
    }

    //if deck.length = 0 we have to reshuffle
    //deals the top card (duh)
    public card dealTop()
    {
        card top = deck[deck.length-1]; //top card
        card[] newDeck = new card[deck.length-2];
        for(int i = 0; i<newDeck.length; i++)
        {
            newDeck[i] = deck[i];
        }
        deck = newDeck;
        return top;
    }
    //shuffles
    public void shuffle()
    {
        for(int i = 0; i<deck.length; i++)
        {
            int rand = (int)(Math.random() * deck.length); //random card
            card temp = deck[rand]; //swaps the cards
            deck[rand] = deck[i];
            deck[i] = temp;
        }
    }
}
