# Sistema Matpi - Gestión de Restaurante

## 📋 Descripción
Sistema integral para la gestión de un restaurante que incluye manejo de pedidos, clientes, empleados, productos y generación de reportes.

## 🎯 Funcionalidades Implementadas

### ✅ Gestión de Pedidos
- **CRUD completo de pedidos** (Crear, Leer, Actualizar, Eliminar)
- **Estados de pedidos**: Pendiente, En Preparación, Entregado, Pagado, Cancelado
- **Asociación con clientes, empleados y reservas**
- **Manejo de productos por pedido** con cantidades

### 📊 Sistema de Reportes
- **Filtros por fecha**: Consultar pedidos en un rango de fechas específico
- **Filtros por estado**: Ver pedidos según su estado actual
- **Estadísticas en tiempo real**:
  - Total de ventas en el período
  - Cantidad de pedidos pendientes
  - Cantidad de pedidos completados
  - Cantidad de pedidos cancelados
- **Exportación** (pendiente implementación completa):
  - Exportar a PDF
  - Exportar a Excel

## 🔧 Configuración de Hibernate

### Auto-creación de Base de Datos

El sistema está configurado para **crear automáticamente las tablas** en PostgreSQL. La configuración se encuentra en `application.properties`:

```properties
# Modo de desarrollo (recrea las tablas cada vez que se inicia)
spring.jpa.hibernate.ddl-auto=create-drop

# Modo de producción (solo actualiza las tablas sin borrar datos)
# spring.jpa.hibernate.ddl-auto=update

# Crear una sola vez
# spring.jpa.hibernate.ddl-auto=create
```

### Opciones de `ddl-auto`:

1. **`create-drop`** (Desarrollo): 
   - Elimina y crea las tablas cada vez que inicia la aplicación
   - ⚠️ **CUIDADO**: Borra todos los datos

2. **`update`** (Producción recomendado):
   - Actualiza el esquema sin borrar datos
   - Mantiene los datos existentes
   - Agrega nuevas columnas/tablas si es necesario

3. **`create`**:
   - Crea las tablas la primera vez
   - No las elimina al cerrar la aplicación

4. **`validate`**:
   - Solo valida que el esquema coincida con las entidades
   - No modifica la base de datos

5. **`none`**:
   - No hace nada automáticamente
   - Requiere scripts SQL manuales

## 🗄️ Estructura de Base de Datos

El sistema creará automáticamente las siguientes tablas:

### Tabla: `pedidos`
```sql
- id (PK)
- fecha 
- estado
- valor
- mesa
- numero_personas
- id_usr_empleado (FK)
- id_cliente (FK)
- id_reserva (FK)
```

### Tabla: `pedido_producto`
```sql
- id (PK)
- pedido_id (FK)
- producto_id (FK)
- cantidad
```

Más tablas: `usuarios`, `empleados`, `clientes`, `productos`, `materias_primas`, `proveedores`, `reservas`, etc.

## 🚀 Instalación en Cualquier Computador

### Requisitos Previos
- Java 17 o superior
- PostgreSQL instalado y ejecutándose
- Lombok plugin instalado en tu IDE

### Pasos:

1. **Clonar el repositorio**
```bash
git clone <url-repositorio>
cd matpi-java
```

2. **Configurar PostgreSQL**
```sql
-- Crear base de datos
CREATE DATABASE matpi;

-- Crear usuario (opcional)
CREATE USER Matpips WITH PASSWORD '12345';
GRANT ALL PRIVILEGES ON DATABASE matpi TO Matpips;
```

3. **Configurar `application.properties`**

Edita el archivo `src/main/resources/application.properties`:

```properties
# Cambia estos valores según tu configuración
spring.datasource.url=jdbc:postgresql://localhost:5432/matpi
spring.datasource.username=Matpips
spring.datasource.password=12345

# Para el primer inicio, usa create-drop o create
spring.jpa.hibernate.ddl-auto=create-drop
```

4. **Compilar el proyecto**
```bash
./gradlew build
```
O si usas Maven:
```bash
mvn clean install
```

5. **Ejecutar la aplicación**
```bash
./gradlew bootRun
```
O si usas Maven:
```bash
mvn spring-boot:run
```

6. **Acceder a la aplicación**
```
http://localhost:8080
```

## 📁 Estructura del Proyecto

```
com.matpi/
├── dominio/
│   ├── dto/                    # Data Transfer Objects
│   │   ├── PedidoDto.java
│   │   └── PedidoProductoDto.java
│   ├── repositorio/            # Interfaces de repositorio
│   │   └── PedidoRepositorio.java
│   └── servicios/              # Lógica de negocio
│       └── PedidoService.java
├── persistencia/
│   ├── entity/                 # Entidades JPA
│   │   ├── PedidoEntity.java
│   │   └── PedidoProductoEntity.java
│   ├── mapper/                 # MapStruct mappers
│   │   ├── PedidoMapper.java
│   │   └── PedidoProductoMapper.java
│   ├── crud/                   # Repositorios JPA
│   │   ├── CrudPedido.java
│   │   └── CrudPedidoProducto.java
│   └── PedidoRepositorioImpl.java
└── web/
    └── controller/             # Controladores REST/MVC
        └── PedidoController.java
```

## 🔌 API Endpoints

### Pedidos - CRUD
```
GET    /pedidos            - Vista HTML de pedidos
GET    /pedidos/api        - Lista todos los pedidos (JSON)
GET    /pedidos/api/{id}   - Obtiene un pedido específico
POST   /pedidos/api        - Crea un nuevo pedido
DELETE /pedidos/api/{id}   - Elimina un pedido
```

### Reportes
```
GET /pedidos/reportes                       - Vista HTML de reportes
GET /pedidos/api/reportes/estado/{estado}   - Pedidos por estado
GET /pedidos/api/reportes/fecha             - Pedidos por rango de fechas
    ?fechaInicio=2024-01-01T00:00:00
    &fechaFin=2024-12-31T23:59:59
GET /pedidos/api/reportes/estadisticas      - Estadísticas generales
    ?fechaInicio=2024-01-01T00:00:00
    &fechaFin=2024-12-31T23:59:59
```

## 📈 Uso de Reportes

1. **Acceder a la sección de reportes**
   - Navegar a `/pedidos/reportes`

2. **Seleccionar filtros**
   - Fecha inicio y fecha fin
   - Estado del pedido (opcional)

3. **Generar reporte**
   - Click en "Generar Reporte"
   - Ver estadísticas y tabla de resultados

4. **Exportar** (próximamente)
   - PDF para impresión
   - Excel para análisis

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 3.x** - Framework principal
- **Spring Data JPA** - ORM y persistencia
- **Hibernate** - Implementación JPA
- **PostgreSQL** - Base de datos
- **MapStruct** - Mapeo de objetos
- **Lombok** - Reducción de código boilerplate
- **Thymeleaf** - Motor de plantillas
- **Gradle/Maven** - Gestión de dependencias

## 📝 Notas Importantes

### Migración entre Computadores

El sistema está diseñado para funcionar en cualquier computador siempre y cuando:

1. PostgreSQL esté instalado
2. La base de datos `matpi` esté creada
3. Las credenciales en `application.properties` sean correctas
4. `ddl-auto` esté configurado apropiadamente

### Recomendaciones de Producción

Para desplegar en producción:

1. Cambiar `ddl-auto` a `validate` o `none`
2. Usar scripts de migración (Flyway o Liquibase)
3. Configurar variables de entorno para credenciales
4. Habilitar SSL para la conexión a BD
5. Configurar respaldos automáticos

## 🔐 Seguridad

```properties
# NO subir credenciales reales al repositorio
# Usa variables de entorno en producción
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

## 📞 Soporte

Para cualquier problema:
1. Verificar que PostgreSQL esté ejecutándose
2. Revisar logs en consola
3. Confirmar que las dependencias estén instaladas
4. Verificar versión de Java (debe ser 17+)

## 🎨 Próximas Funcionalidades

- [ ] Exportación real a PDF
- [ ] Exportación real a Excel
- [ ] Gráficos de ventas
- [ ] Reportes por empleado
- [ ] Reportes por producto más vendido
- [ ] Dashboard de métricas en tiempo real
- [ ] Notificaciones de pedidos

---

**Desarrollado para Matpi Restaurant** 🍔
