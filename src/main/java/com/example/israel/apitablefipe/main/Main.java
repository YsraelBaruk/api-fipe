package com.example.israel.apitablefipe.main;

import com.example.israel.apitablefipe.model.Dados;
import com.example.israel.apitablefipe.model.Modelos;
import com.example.israel.apitablefipe.model.Veiculo;
import com.example.israel.apitablefipe.service.ConsumoApi;
import com.example.israel.apitablefipe.service.ConvertDate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    //    private Veiculo veiculo = new Veiculo();
    private Scanner typed = new Scanner(System.in);
    private ConsumoApi comsumo = new ConsumoApi();
    private ConvertDate conversor = new ConvertDate();

    private final String url = "https://parallelum.com.br/fipe/api/v1/";

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
        if (option.toLowerCase().contains("carr")){
            endereco = url+"carros/marcas";
        } else if (option.toLowerCase().contains("mot")) {
            endereco = url+"motos/marcas";
        } else {
            endereco = url+"caminhoes/marcas";
        }

        var json = comsumo.obterDados(endereco);
        System.out.println(json);
        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var codeMarca = typed.nextLine();

        endereco = endereco+"/"+codeMarca+"/modelos";
        json = comsumo.obterDados(endereco);
        var modeloLista = conversor.obterDados( json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado");
        var nomeVeiculo = typed.nextLine();
        List<Dados> modelosFiltro = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .toList();
//                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados");
        modelosFiltro.forEach(System.out::println);

        System.out.println("Digite por favor o código do modelo para a busca de avaliação");
        var codigoModelo = typed.nextLine();

        endereco = "%s/%s/anos".formatted(endereco, codigoModelo);
        json = comsumo.obterDados(endereco);
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = "%s/%s".formatted(endereco, anos.get(i).codigo());
            json = comsumo.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("\nTodos veículos filtrados por ano");
        veiculos.forEach(System.out::println);
    }
}
