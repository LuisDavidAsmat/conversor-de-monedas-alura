# 💱 Conversor de Monedas

Aplicación de consola desarrollada en Java que permite convertir montos entre diferentes monedas utilizando la API de [ExchangeRate-API](https://www.exchangerate-api.com/). Además, guarda un historial de consultas en un archivo de texto para referencia futura.

---

## 📌 Características

- Opción para introducir códigos personalizados de moneda.
- Visualización de una lista de monedas disponibles.
- Registro automático de cada consulta con fecha y hora.
- Estructura modular y fácilmente extensible.

---

## 🛠️ Tecnologías utilizadas

- **Java 17+**
- **GSON** – para el manejo de JSON.
- **HTTP Client (java.net.http)** – para realizar solicitudes a la API.

---

## 📂 Estructura del proyecto
```
src/
├── data/
│ ├── CurrencyList.java
│
├── models/
│ ├── ConversionRequest.java
│
├── services/
│ ├── ExchangeRateFetcher.java
| ├── ConversionService.java
| ├── FileExporter.java
│
├── ui/
│ ├── ConsoleUI.java
│
├── Main.java
```
## 🧪 Ejemplo de uso
```
***************************************************
*** Sea bienvenido al Conversor de Monedas ***
1) Peso Mexicano -> Dólar
2) Peso Mexicano -> Euro
...
7) Otra opción personalizada
8) Salir
***************************************************
```


-- Proyecto desarrollado como parte de la especialización Backend ONE de Alura y Oracle .

