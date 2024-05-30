package com.example.israel.ApiTabelaFipe.main;

import com.example.israel.ApiTabelaFipe.model.Veiculo;
import com.example.israel.ApiTabelaFipe.service.ConsumoApi;

import java.util.Scanner;

public class Main {
    private Scanner typed = new Scanner(System.in);
    private ConsumoApi comsumo = new ConsumoApi();
    private final String url = "https://parallelum.com.br/fipe/api/v1/";
    private Veiculo veiculo = new Veiculo();

    public void getMenu(){
        String menu = """
                ***Opções***
                
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar:
                """;
        System.out.println(menu);
        var option = typed.nextLine();
        String endereco;
        int diaDaSemana = 0;
        if (option.toLowerCase().contains("carr")){
            endereco = url+"carros/marcas";
        } else if (option.toLowerCase().contains("mot")) {
            endereco = url+"motos/marcas";
        } else {
            endereco = url+"caminhoes/marcas";
        }
        var json = comsumo.obterDados(endereco);
        System.out.println(json);
    }
}
