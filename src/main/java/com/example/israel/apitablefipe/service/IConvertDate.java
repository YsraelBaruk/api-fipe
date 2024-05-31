package com.example.israel.apitablefipe.service;

import java.util.List;

public interface IConvertDate {
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
