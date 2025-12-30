# VolandoVoy API

API REST para gestionar vuelos. Permite crear, consultar, actualizar y eliminar vuelos, así como filtrar y ordenar la lista de vuelos.

---

## Endpoints

| Método | URL                                    | Descripción                                     |
| ------ | -------------------------------------- | ----------------------------------------------- |
| GET    | `/vuelos`                              | Obtener todos los vuelos, opcionalmente filtrados y ordenados |
| GET    | `/vuelos/{id}`                         | Obtener un vuelo por su ID                      |
| POST   | `/vuelos`                              | Crear un nuevo vuelo                             |
| PUT    | `/vuelos/{id}`                         | Actualizar un vuelo existente                   |
| DELETE | `/vuelos/{id}`                         | Eliminar un vuelo por ID                         |

---

## Filtrado y Ordenación

Se pueden usar parámetros opcionales en `GET /vuelos`:

- `empresa`: filtra por aerolínea
- `lugarLlegada`: filtra por destino
- `fechaSalida`: filtra por fecha de salida (`YYYY-MM-DD`)
- `ordenarPor`: campo por el que ordenar (`fechaSalida`, `empresa`, `lugarLlegada`)
- `orden`: dirección de orden (`ASC` o `DESC`)

**Ejemplos:**

- `/vuelos?empresa=Turkish` → vuelos de Turkish Airlines
- `/vuelos?lugarLlegada=New York&ordenarPor=fechaSalida&orden=ASC` → vuelos a New York ordenados por fecha ascendente

---

## Ejemplos de JSON

### Crear un vuelo (POST /vuelos)

```json
{
  "nombreVuelo": "FR123",
  "empresa": "Air France",
  "lugarSalida": "Paris",
  "lugarLlegada": "Madrid",
  "fechaSalida": "2025-11-01",
  "fechaLlegada": "2025-11-02"
}
