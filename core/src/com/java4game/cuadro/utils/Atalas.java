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

	Texture mn;

    ArrayList<Integer> q1q = new ArrayList<Integer>(120);
    ArrayList<Integer> q2q = new ArrayList<Integer>(120);
    ArrayList<Integer> q3q = new ArrayList<Integer>(120);
    ArrayList<Integer> q4q = new ArrayList<Integer>(120);


    //инструкция по добавлению новой текстуры


    /*
    *  после того, как мы добавили новый атлас текстур с координатами и размерами каждого региона текстур
    *  мы должны создать поле string с названием текстуры и добавить его в массив **names**
    *
    * */


    public static final String sfieldT = "sFieldT";
    public static final String squareT = "squareT";


    public static final String[] names = new String[] {sfieldT, squareT };

    public static int textsCount;


	public Atalas() {
        String file;
        float cff;


        file = "atls1.png";
        cff = 1f;

        mn = new Texture(file);




		mn.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		ArrayList<Integer> numb = new ArrayList<Integer>();

		String str, str2 = "";

		Integer hq[], jd = 0;

		hq = new Integer[6];

        str = Gdx.files.internal("atls1.txt").readString();


		for (char element : str.toCharArray()) {
			if (element != ' ') {
				str2 = str2 + String.valueOf(element);
			} else {
				numb.add(Integer.parseInt(str2));
				str2 = "";
			}
		}

		for (int i = 0; i < numb.size() - 1; i++) {
			hq[jd] = numb.get(i);
			jd++;
			if (jd == 4) {
				jd = 0;



				q1q.add((int) (hq[0] * cff));
				q2q.add((int) (hq[1] * cff));
				q3q.add((int) (hq[2] * cff));
				q4q.add((int) (hq[3] * cff));

			}

		}
		hq[3] = numb.get(numb.size() - 1);

        q1q.add((int) (hq[0] * cff));
        q2q.add((int) (hq[1] * cff));
        q3q.add((int) (hq[2] * cff));
        q4q.add((int) (hq[3] * cff));
        textsCount = q1q.size();

        numb = null;
	}
	
	public void dispose(){
        q1q = null;
        q2q = null;
        q3q = null;
        q4q = null;
		mn.dispose();
	}

	public TextureRegion getTG(int wg) {
		return new TextureRegion(mn, q1q.get(wg), q2q.get(wg), q3q.get(wg),
				q4q.get(wg));
	}
}
