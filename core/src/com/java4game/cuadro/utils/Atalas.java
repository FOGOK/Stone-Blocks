package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Atalas {

    /**
     * Вспомогательный класс, хранящий в себе все регионы текстур
     * */

	Texture mainTexture;

    ArrayList<Integer> reg1Coords = new ArrayList<Integer>(120);
    ArrayList<Integer> reg2Coords = new ArrayList<Integer>(120);
    ArrayList<Integer> reg3Coords = new ArrayList<Integer>(120);
    ArrayList<Integer> reg4Coords = new ArrayList<Integer>(120);


    //инструкция по добавлению новой текстуры


    /*
    *  после того, как мы добавили новый атлас текстур с координатами и размерами каждого региона текстур
    *  мы должны создать поле string с названием текстуры и добавить его в массив **NAMES**
    *
    * */


    public static final int sq00 = 0;
    public static final int sq01 = 1;
    public static final int sq02 = 2;
    public static final int sq03 = 3;
    public static final int sq04 = 4;
    public static final int sq05 = 5;
    public static final int sq06 = 6;
    public static final int sq07 = 7;
    public static final int sq08 = 8;
    public static final int sq09 = 9;
    public static final int sq10 = 10;
    public static final int sq11 = 11;
    public static final int squareT1 = 12;
    public static final int squareT2 = 13;
    public static final int squareT3 = 14;
    public static final int num1 = 15;
    public static final int num2 = 16;
    public static final int num3 = 17;
    public static final int num4 = 18;
    public static final int num5 = 19;
    public static final int num6 = 20;
    public static final int num7 = 21;
    public static final int num8 = 22;
    public static final int num9 = 23;
    public static final int col1 = 24;
    public static final int col2 = 25;
    public static final int col3 = 26;
    public static final int col4 = 27;
    public static final int starSq = 28;
    public static final int gemSq = 29;
    public static final int comboText = 30;
    public static final int combNum0 = 31;
    public static final int combNum1 = 32;
    public static final int combNum2 = 33;
    public static final int combNum3 = 34;
    public static final int combNum4 = 35;
    public static final int combNum5 = 36;
    public static final int combNum6 = 37;
    public static final int combNum7 = 38;
    public static final int combNum8 = 39;
    public static final int combNum9 = 40;
    public static final int startB = 41;
    public static final int startBAct = 42;
    public static final int upBarMenu = 43;
    public static final int gameNameT = 44;
    public static final int downBarMenu = 45;
    public static final int btnStage = 46;
    public static final int btnStageLock = 47;
    public static final int btnStageAct = 48;
    public static final int worldBtn1 = 49;
    public static final int worldBtn2 = 50;
    public static final int worldBtn3 = 51;
    public static final int worldBtn4 = 52;
    public static final int worldBtn5 = 53;
    public static final int worldBtnLock = 54;
    public static final int star = 55;
    public static final int starNotAct = 56;
    public static final int star1 = 57;
    public static final int star2 = 58;
    public static final int star3 = 59;
    public static final int noStar = 60;
    public static final int listCover = 61;

    private final static int X = 0, Y = 1, W = 2, H = 3, NEXTELEMENT = 4;

	public Atalas() {
        String fileName = "atls1.png";
        float cff;
        cff = 1f;

        mainTexture = new Texture(fileName);




		mainTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		ArrayList<Integer> allValues = new ArrayList<Integer>();
		String allElementsString, currentElementString = "";

		Integer elementIteration[], currentElement = 0;
		elementIteration = new Integer[6];

        allElementsString = Gdx.files.internal("atls1.txt").readString();


		for (char currentElementChar : allElementsString.toCharArray()) {
			if (currentElementChar != ' ') {
				currentElementString = currentElementString + String.valueOf(currentElementChar);
			} else {
				allValues.add(Integer.parseInt(currentElementString));
				currentElementString = "";
			}
		}

		for (int i = 0; i < allValues.size() - 1; i++) {
			elementIteration[currentElement] = allValues.get(i);
			currentElement++;
			if (currentElement == NEXTELEMENT) {
				reg1Coords.add((int) (elementIteration[X] * cff));
				reg2Coords.add((int) (elementIteration[Y] * cff));
				reg3Coords.add((int) (elementIteration[W] * cff));
				reg4Coords.add((int) (elementIteration[H] * cff));

                currentElement = X;
			}

		}
		elementIteration[3] = allValues.get(allValues.size() - 1);

        reg1Coords.add((int) (elementIteration[0] * cff));
        reg2Coords.add((int) (elementIteration[1] * cff));
        reg3Coords.add((int) (elementIteration[2] * cff));
        reg4Coords.add((int) (elementIteration[3] * cff));
	}
	
	public void dispose(){
		mainTexture.dispose();
	}

	public TextureRegion getTG(int numTexture) {
		return new TextureRegion(mainTexture, reg1Coords.get(numTexture), reg2Coords.get(numTexture), reg3Coords.get(numTexture),
				reg4Coords.get(numTexture));
	}
}
