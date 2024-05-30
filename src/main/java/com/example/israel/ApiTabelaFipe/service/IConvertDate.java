package com.example.israel.ApiTabelaFipe.service;

public interface IConvertDate {
    <T> T obterDados(String json, Class<T> classe);
}
