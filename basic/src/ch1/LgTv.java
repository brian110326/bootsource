package ch1;

public class LgTv implements Tv {

    // 멤버변수 초기화
    // 기본형 : int, long, boolean...
    // 1) 정수타입 : 0
    // 2) 논리형타입 : false
    // 3) 실수형타입 : 0.0

    // 참조형 : null로 초기화
    private Speaker speaker; // BritzSpeaker britzSpeaker == null;

    // 멤버 변수 초기화 방법
    // 1) setter 메소드
    // 2) 생성자 이용

    public LgTv(Speaker speaker) {
        this.speaker = speaker;
    }

    public LgTv() {
    }

    @Override
    public void powerOn() {
        System.out.println("LgTv - 전원 On");
    }

    @Override
    public void powerOff() {
        System.out.println("LgTv - 전원 Off");
    }

    @Override
    public void volumeUp() {
        // System.out.println("LgTv - Volume Up");
        speaker.volumeUp();
    }

    @Override
    public void volumeDown() {
        // System.out.println("LgTv - Volume Down");
        speaker.volumeDown();
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

}
