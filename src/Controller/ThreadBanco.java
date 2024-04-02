package Controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {


    private Semaphore semaforoSaque;
    private Semaphore semaforoDeposito;

    private int cod;
    private int saldo;
    private int trans;

    public ThreadBanco(int cod, int saldo, int trans, Semaphore semaforoSaque, Semaphore semaforoDeposito) {
        this.cod = cod;
        this.saldo = saldo;
        this.trans = trans;
        this.semaforoSaque = semaforoSaque;
        this.semaforoDeposito = semaforoDeposito;

    }

    @java.lang.Override
    public void run() {

        if(cod * ((int) (Math.random() * 10)) % 2 == 0) {
            try {

                semaforoDeposito.acquire();
                deposito();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforoDeposito.release();
            }

        } else {
            try {

                semaforoSaque.acquire();
                saque();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforoSaque.release();
            }

        }


    }

    private void saque() {

        System.out.println("Transação da conta #" + cod + " de operação saque está ocorrendo \nSaldo inicial: " + saldo);
        saldo = saldo - trans;
        try {
            sleep(1000);
        } catch (Exception e) {
                e.printStackTrace();
        }

        System.out.println("Saque da conta #" + cod + " de R$" + trans + " finalizado \n Saldo final de R$" + saldo);

    }

    private void deposito() {

        System.out.println("Transação da conta #" + cod + " de operação depósito está ocorrendo \nSaldo Inicial: " + saldo);
        saldo = saldo + trans;
        try {
            sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Depósito da conta #" + cod + " de R$" + trans + " finalizado \n Saldo final de R$" + saldo);

    }
}
