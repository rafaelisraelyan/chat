package rafa.client;

import java.awt.Color;

public class Colors {
    public static Color mainColor = new Color(0x232222);//0x232222 0xD2D2D2
    public static Color sideColor = new Color(0x232225);//0x232225 0xD4D3DA
    public static Color mainTxtColor = new Color(0xffffff);//0xffffff 0x000000
    public static Color sideTxtColor = new Color(0xbbbbbb);//0xbbbbbb 0x444444
    public static Color mainBorderColor = new Color(0x3f3f3f);//0x3f3f3f 0xBEBEBE
    public static Color sideBorderColor = new Color(0x333333);//0x333333 0xC1C1C1
    public static Color tabColor = new Color(0x232220);//0x232227 0xD3C6D8
    public static Color myMsqColor = new Color(0x574cc9);//0x574cc9 0xAC9E1C
    public static Color msqColor = new Color(0x4F5155);//0xE1E4E8 0x538697

    public Colors(Color mainColor,Color sideColor,Color mainTxtColor,Color sideTxtColor,
                  Color mainBorderColor,Color sideBorderColor,Color tabColor, Color myMsqColor,
                  Color msqColor){
        Colors.mainColor = mainColor;
        Colors.sideColor = sideColor;
        Colors.mainTxtColor = mainTxtColor;
        Colors.sideTxtColor = sideTxtColor;
        Colors.mainBorderColor = mainBorderColor;
        Colors.sideBorderColor = sideBorderColor;
        Colors.tabColor = tabColor;
        Colors.myMsqColor = myMsqColor;
        Colors.msqColor = msqColor;
    }
}
