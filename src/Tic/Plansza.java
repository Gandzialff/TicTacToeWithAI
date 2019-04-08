package Tic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plansza {
    public static final int PUSTE_POLE = 0;
    public static final int GRACZ_X = 1;
    public static final int GRACZ_O = 2;
    private int[][] plansza = new int[3][3];
    public Punkt ruchKomputera;
    Random r = new Random();



    /**
     * Metoda kończąca gre gdy zostanie spełniony jeden z warunków
     *
     * @return true/false
     */
    public boolean koniecGry() {
        if (wygrywaGracz(GRACZ_X) || wygrywaGracz(GRACZ_O) || pobierzDostepneKomorki().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Metoda sprawdzająca wszystkie kombinacje wygrywające
     *
     * @param gracz
     * @return true/false
     */
    public boolean wygrywaGracz(int gracz) {
        if ((plansza[0][0] == plansza[1][1] && plansza[0][0] == plansza[2][2] && plansza[0][0] == gracz)
                ||
                plansza[0][2] == plansza[1][1] && plansza[0][2] == plansza[2][0] && plansza[0][2] == gracz) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if ((plansza[i][0] == plansza[i][1] && plansza[i][0] == plansza[i][2] && plansza[i][0] == gracz)
                    ||
                    (plansza[0][i] == plansza[1][i] && plansza[0][i] == plansza[2][i] && plansza[0][i] == gracz)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda sprawdza puste komórki na planszy
     * i zwraca je w liście
     *
     * @return dostepneKomorki
     */
    public List<Punkt> pobierzDostepneKomorki() {
        List<Punkt> dostepneKomorki = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (plansza[i][j] == PUSTE_POLE) {
                    dostepneKomorki.add(new Punkt(i, j));
                }
            }
        }
        return dostepneKomorki;
    }

    /**
     * Umieszcza ruch gracza lub kumputera na planszy
     *
     * @param punkt
     * @param gracz
     * @return
     */
    public boolean umiescRuchNaPlanszy(Punkt punkt, int gracz) {
        if (plansza[punkt.x][punkt.y] != PUSTE_POLE) {
            return false;
        }

        plansza[punkt.x][punkt.y] = gracz;
        return true;
    }

    /**
     * Metoda wyświetlająca planszę
     */
    public void wyswietlPlansze() {
        System.out.println();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String value = "?";
                if (plansza[i][j] == GRACZ_X) {
                    value = "X";
                } else if (plansza[i][j] == GRACZ_O) {
                    value = "O";
                }
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }



    public boolean AI(int tura) {



        switch (tura){
            case 1: //Gdy zaczyna komputer
                int gdzie = r.nextInt(5);
                switch (gdzie){
                    case 0:
                        ruchKomputera = new Punkt(1,1);
                        break;
                    case 1:
                        ruchKomputera = new Punkt(0,0);
                        break;
                    case 2:
                        ruchKomputera = new Punkt(0,2);
                        break;
                    case 3:
                        ruchKomputera = new Punkt(2,0);
                        break;
                    case 4:
                        ruchKomputera = new Punkt(2,2);
                        break;
                    default:
                        break;
                }
            break;
            case 2: //Gdy zaczyna Gracz
                if(plansza[1][1] == 2){ //Gdy gracz da w środku
                    int gdzie2 = r.nextInt(4);
                    switch (gdzie2){
                        case 0:
                            ruchKomputera = new Punkt(0,0);
                            break;
                        case 1:
                            ruchKomputera = new Punkt(0,2);
                            break;
                        case 2:
                            ruchKomputera = new Punkt(0,0);
                            break;
                        case 3:
                            ruchKomputera = new Punkt(2,2);
                            break;
                        default:
                            break;
                    }
                }
                else if(plansza[0][0] == 2 || plansza[0][2] == 2 || plansza[2][0] == 2 || plansza[2][2] == 2){ // Gdy gracz da na rogu
                    ruchKomputera = new Punkt(1,1);
                }
                else { // Gracz da na krawędzi
                    if(plansza[0][1] == 2 || plansza[1][0] == 2){
                        ruchKomputera = new Punkt(0,0);
                    }else if(plansza[1][2] == 2 || plansza[2][1] == 2){
                        ruchKomputera = new Punkt(2,2);
                    }

                }
            break;
                default:
                    int gx,go,pp;
                    //sprawdz poziomo
                    Punkt p = new Punkt();
                    for(int i = 0;i<3; i++){
                        gx =0;
                        go =0;
                        pp =0;
                        for (int j = 0;j<3;j++){
                            if(plansza[i][j] == GRACZ_O){
                                go++;
                            }else if(plansza[i][j] == GRACZ_X){
                                gx++;
                            }else if(plansza[i][j] == PUSTE_POLE){
                                pp++;
                                p.x =i;
                                p.y =j;
                            }
                            if((gx == 2) && pp == 1){
                                ruchKomputera = p;
                                return true;
                            }
                        }
                        if((gx == 2 || go == 2) && pp == 1){
                            ruchKomputera = p;
                            return true;
                        }
                    }

                    //sprawdz pionowo
                    for(int i =0;i<3;i++){
                        gx = 0;
                        go = 0;
                        pp = 0;
                        for (int j = 0;j<3;j++){
                            if(plansza[j][i] == GRACZ_O){
                                go++;
                            }else if(plansza[j][i] == GRACZ_X){
                                gx++;
                            }else if(plansza[j][i] == PUSTE_POLE){
                                pp++;
                                p.x = j;
                                p.y = i;
                            }
                            if(gx==2 && pp == 1){
                                ruchKomputera = p;
                                return true;
                            }
                        }
                        if ((gx == 2 || go == 2) && pp == 1){
                            ruchKomputera = p;
                            return true;
                        }
                    }
                    //sprawdz przekątne
                    gx=0;
                    go=0;
                    pp=0;
                    if(plansza[0][0] == GRACZ_X) gx++;
                    if(plansza[1][1] == GRACZ_X) gx++;
                    if(plansza[2][2] == GRACZ_X) gx++;

                    if(plansza[0][0] == GRACZ_O) go++;
                    if(plansza[1][1] == GRACZ_O) go++;
                    if(plansza[2][2] == GRACZ_O) go++;

                    if(plansza[0][0] == PUSTE_POLE){
                        p.x =0;
                        p.y =0;
                        pp++;
                    }
                    if(plansza[1][1] == PUSTE_POLE){
                        p.x = 1;
                        p.y = 1;
                        pp++;
                    }
                    if(plansza[2][2] == PUSTE_POLE) {
                        p.x = 2;
                        p.y = 2;
                        pp++;
                    }
                    if ((gx == 2 || go == 2) && pp == 1){
                        ruchKomputera = p;
                        return true;
                    }
                    gx=0;
                    go=0;
                    pp=0;
                    if(plansza[0][2] == GRACZ_X) gx++;
                    if(plansza[1][1] == GRACZ_X) gx++;
                    if(plansza[2][0] == GRACZ_X) gx++;

                    if(plansza[0][2] == GRACZ_O) go++;
                    if(plansza[1][1] == GRACZ_O) go++;
                    if(plansza[2][0] == GRACZ_O) go++;

                    if(plansza[0][2] == PUSTE_POLE) {
                        p.x = 0;
                        p.y = 2;
                        pp++;
                    }
                    if(plansza[1][1] == PUSTE_POLE) {
                     p.x = 1;
                     p.y = 1;
                     pp++;
                    }
                    if(plansza[2][0] == PUSTE_POLE){
                        p.x = 2;
                        p.y = 0;
                        pp++;
                    }

                    if ((gx == 2 || go == 2) && pp == 1){
                        ruchKomputera = p;
                        return true;
                    }

                    List<Punkt> dostepne = pobierzDostepneKomorki();
                    int los = r.nextInt(dostepne.size());
                    ruchKomputera = dostepne.get(los);
                    break;
        }
        return false;
    }

    }

