package basics.midi;

import javax.sound.midi.*;

public class Test {
    public static void main(String[] args) {
        startMidi();
    }

    /**
     * Sequencer : CD播放器
     * Sequence : 单曲CD
     * Track : 歌谱
     * MidiEvent : 一个音符
     */
    public static void startMidi(){
        try {
            //取得Sequencer并打开它
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            //创建新的Sequence
            Sequence sequence = new Sequence(Sequence.PPQ,4);
            //从Sequence中创建新的Track对象
            Track track = sequence.createTrack();
            //填入MidiEvent并让Sequencer播放
            ShortMessage message = new ShortMessage();
            //开关类型，哪种音效，音符，音量
            message.setMessage(144,1,32,100);
            //乐谱的第几拍
            track.add(new MidiEvent(message,1));
            sequencer.setSequence(sequence);
            sequencer.start();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}
