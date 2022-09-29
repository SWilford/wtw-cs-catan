import javax.sound.midi.*;

public class catanSound {
    protected static MidiChannel[] channels = null;
    private static int soundOffFrame, nextNoteFrame;
    protected static int [] pedalTones;
    protected static final int [] majPent = {0,2,4,7,9,12};
    protected static final int [] minPent = {0,3,5,7,10,12};
    protected static final int [] harmMin = {0,2,3,5,7,8,11,12};
    protected static int [] sustainLengths;

    protected static int [] noteLengths;
    protected static int [] scale;
    protected static Instrument[] instr;
    protected static final int PIANO = 0;
    protected static final int TIMPANI = 47;
    protected static final int FRET_NOISE = 55;
    protected static final int GT_HARMONIC = 31;
    protected static final int HARPSICHORD = 6;
    protected static final int VOICE_OOHS = 53;
    protected static final int FRENCH_HORN = 60;
    protected static final int WHISTLE = 78;
    protected static final int ELECTRIC_GT_JAZZ = 26;
    protected static final int ELECTRIC_GT_CLEAN = 27;

    protected static final int WOODBLOCK = 115;

    public static void initialize()
    {
        try
        {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            catanSound.channels = synth.getChannels();
            catanSound.instr = synth.getDefaultSoundbank().getInstruments();
        }
        catch (Exception ignored)
        {}
        channels[0].programChange(instr[PIANO].getPatch().getProgram());
        buildScale(harmMin, minPent);
        noteLengths = new int[9];
        int wholeNote = 8*12;
        noteLengths[0] = Math.max(1,wholeNote/16);
        noteLengths[1] = Math.max(2,wholeNote/8);
        noteLengths[2] = Math.max(2,wholeNote/8);
        noteLengths[3] = Math.max(4,wholeNote/4);
        noteLengths[4] = Math.max(4,wholeNote/4);
        noteLengths[5] = Math.max(4,wholeNote/4);
        noteLengths[6] = Math.max(8,wholeNote/2);
        noteLengths[7] = Math.max(8,wholeNote/2);
        noteLengths[8] = Math.max(16,wholeNote);

        sustainLengths = new int[8];
        sustainLengths[0] = Math.max(16,wholeNote);
        sustainLengths[1] = Math.max(16,wholeNote);
        sustainLengths[2] = Math.max(16,wholeNote);
        sustainLengths[3] = Math.max(8,wholeNote/2);

        sustainLengths[4] = Math.max(16,wholeNote);
        sustainLengths[5] = Math.max(8,wholeNote/2);
        sustainLengths[6] = Math.max(8,wholeNote/2);
        sustainLengths[7] = Math.max(4,wholeNote/4);
        silence();
    }

    public static void silence()
    {
        channels[0].allNotesOff();
    }

    public static void click()
    {
        channels[0].programChange(instr[WOODBLOCK].getPatch().getProgram());
        int pitch = 102;
        int velocity = 115;
        channels[0].noteOn(pitch, velocity);
    }
    public static void runBackgroundSounds()
    {
        int screen = catanGraphics.getCurrentScreen();
        if(screen == 1)
        {
            int note = scale[(int) (Math.random() * scale.length)];
            final int firstNote = note;

            if(catanGraphics.getFrames() > nextNoteFrame) {
                channels[0].programChange(instr[HARPSICHORD].getPatch().getProgram());
                int velocity = 40;
                if(Math.random() <.25)
                {
                    channels[0].noteOn(firstNote, velocity);
                }
                else
                    channels[0].noteOn(note, velocity);
                int noteLength = noteLengths[(int)(Math.random()*(noteLengths.length))];
                nextNoteFrame = catanGraphics.getFrames() + noteLength * (3/2);
                soundOffFrame = catanGraphics.getFrames() + noteLength;
            }
        }
    }

    public static void checkToClearSound()
    {
        if(catanGraphics.getFrames() > soundOffFrame)
        {
            silence();						//turn sounds off
            soundOffFrame = Integer.MAX_VALUE;
        }
    }

    public static void buildScale(int [] baseScale, int [] pedalScale)
    {
        int randKey = (int)(Math.random() * 11) + 60;

        int [] ans = new int[baseScale.length];
        for(int i=0; i<ans.length; i++)
            ans[i] = baseScale[i] + randKey;
        scale = ans;

        ans = new int[pedalScale.length];
        for(int i=0; i<ans.length; i++)
            ans[i] = pedalScale[i] + randKey;
        pedalTones = ans;
    }
}


