package Tic;

import java.util.Random;
import java.util.Scanner;

public class KolkoKrzyzyk {
    public static final Random RANDOM = new Random();


    public static void main(String[] args) {
        while(true){
            int tura = 1;
            Plansza plansza = new Plansza();
            Scanner s = new Scanner(System.in);
            int wybor;

            plansza.wyswietlPlansze();

            System.out.print("Wybierz kto zaczyna: \n1.Komputer(X) \n2.Gracz(O)");

            while (true){
                System.out.print("\nWybierz: ");
                wybor = s.nextInt();
                if(wybor==1||wybor==2){
                    break;
                }
                else {
                    System.err.println("Podałeś nieprawidłowe dane! Spróbuj ponownie");
                }
            }

            if(wybor == Plansza.GRACZ_X){
                System.out.println("Tura: "+tura);
                plansza.AI(tura);
                plansza.umiescRuchNaPlanszy(plansza.ruchKomputera,Plansza.GRACZ_X);
                plansza.wyswietlPlansze();
                tura++;
            }

            while (!plansza.koniecGry()){
                boolean czyWolnePole = true;
                do {
                    if(!czyWolnePole){
                        System.err.println("To pole jest zajęte!");
                    }
                    int a,b;
                    while (true){
                        System.out.println("Tura: "+tura);
                        System.out.println("Twój ruch: ");
                        a = s.nextInt();
                        b = s.nextInt();
                        if(a >= 0 && a <3 && b >=0 && b < 3){
                            break;
                        }
                        else {
                            System.err.println("Podane dane wychodzą poza zakres planszy!");
                        }
                    }
                    Punkt ruchGracza = new Punkt(a,b);
                    czyWolnePole = plansza.umiescRuchNaPlanszy(ruchGracza,Plansza.GRACZ_O);
                }while (!czyWolnePole);
                tura++;
                plansza.wyswietlPlansze();

                if(plansza.koniecGry()){
                    break;
                }
                System.out.println("Tura: "+tura);
                plansza.AI(tura);
                System.out.println("Komputer wybiera pozycje: "+plansza.ruchKomputera);

                plansza.umiescRuchNaPlanszy(plansza.ruchKomputera,Plansza.GRACZ_X);
                plansza.wyswietlPlansze();
                tura++;
            }
            if(plansza.wygrywaGracz(Plansza.GRACZ_X)){
                System.out.println("Przegrałeś!");
            }else if(plansza.wygrywaGracz(Plansza.GRACZ_O)){
                System.out.println("Wygrałeś!");
            }else {
                System.out.println("REMIS!");
            }

            System.out.println("Czy chesz zagrać ponownie ? (1-tak, [kazdy inny znak]-nie");
            short odnowa;
            odnowa = s.nextShort();
            if(odnowa != 1){
                break;
            }

        }


    }
}
