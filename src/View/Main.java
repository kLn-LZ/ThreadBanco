package View;

import Controller.ThreadBanco;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Semaphore semaforoSaque = new Semaphore(1);
        Semaphore semaforoDeposito = new Semaphore(1);

        for (int i = 0; i < 20; i++) {
            int saldo = (int) (Math.random() * 151) + 50;
            int trans = (int) (Math.random() * saldo) + 10;
            Thread tBanco = new ThreadBanco(i, saldo, trans, semaforoSaque, semaforoDeposito );
            tBanco.start();

        }

    }


}
