# Sistema de Gestión MATPI - Módulo de Pedidos y Reportes

## 📋 Descripción

Este proyecto es un sistema integral de gestión para restaurantes desarrollado en **Spring Boot** con vistas **Thymeleaf**. Incluye un completo sistema de gestión de pedidos y generación de reportes estadísticos.

## ✨ Características Implementadas

### 🛒 Gestión de Pedidos (CRUD Completo)

- ✅ **Listar pedidos** con filtros por estado y rango de fechas
- ✅ **Crear pedidos** con múltiples productos
- ✅ **Ver detalles** de pedidos individuales
- ✅ **Editar pedidos** existentes
- ✅ **Eliminar pedidos**
- ✅ Estados de pedido: Pendiente, En Preparación, Entregado, Pagado, Cancelado

### 📊 Sistema de Reportes

- ✅ Generación de reportes con filtros personalizables
- ✅ Estadísticas por estado de pedidos
- ✅ Totales de ventas y cantidad de pedidos
- ✅ Gráfico de torta (Chart.js) con distribución por estado
- ✅ Tabla detallada de pedidos filtrados
- ✅ Función de impresión de reportes

### 🗄️ Base de Datos Auto-Creada con Hibernate

- ✅ Configuración de Hibernate para auto-crear la base de datos
- ✅ El proyecto funciona en cualquier computador con PostgreSQL instalado
- ✅ Solo necesitas configurar las credenciales en `application.properties`

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas:

```
matpi-java/
├── src/main/java/com/matpi/
│   ├── web/controller/          # Controladores MVC
│   │   ├── PedidoController.java
│   │   └── ReporteController.java
│   ├── dominio/
│   │   ├── servicios/           # Lógica de negocio
│   │   │   ├── PedidoService.java
│   │   │   └── ReporteService.java
│   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── PedidoDto.java
│   │   │   ├── PedidoProductoDto.java
│   │   │   └── ReporteDto.java
│   │   └── repositorio/         # Interfaces de repositorio
│   │       └── PedidoRepositorio.java
│   └── persistencia/
│       ├── entity/              # Entidades JPA
│       │   ├── PedidoEntity.java
│       │   └── PedidoProductoEntity.java
│       ├── crud/                # JPA Repositories
│       │   └── CrudPedido.java
│       └── PedidoRepositorioImpl.java
│
└── src/main/resources/
    ├── templates/               # Vistas Thymeleaf
    │   ├── pedidos.html
    │   ├── pedido-crear.html
    │   ├── pedido-editar.html
    │   ├── pedido-detalle.html
    │   └── reportes.html
    └── application.properties
```

## 🚀 Configuración e Instalación

### Requisitos Previos

- Java 17 o superior
- PostgreSQL 12 o superior
- Gradle 7.x o superior

### Paso 1: Configurar PostgreSQL

1. Instala PostgreSQL en tu computador
2. Crea una base de datos llamada `matpi`:

   ```sql
   CREATE DATABASE matpi;
   ```

### Paso 2: Configurar Credenciales

Edita el archivo `src/main/resources/application.properties`:

```properties
# Cambia estos valores según tu configuración
spring.datasource.url=jdbc:postgresql://localhost:5432/matpi
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
```

### Paso 3: Ejecutar el Proyecto

```bash
# Con Gradle
./gradlew bootRun

# O con Gradle Wrapper en Windows
gradlew.bat bootRun
```

La aplicación estará disponible en: `http://localhost:8080`

## 📝 Uso del Sistema

### Gestión de Pedidos

1. **Acceder al módulo**: Navega a `/pedidos`
2. **Crear un pedido**:
   - Clic en "Nuevo Pedido"
   - Completa la información (mesa, empleado, estado)
   - Agrega productos con el botón "Agregar Producto"
   - Guarda el pedido
3. **Filtrar pedidos**: Usa los filtros por estado y fechas
4. **Editar/Eliminar**: Usa los botones de acción en cada fila

### Reportes

1. **Acceder a reportes**: Navega a `/reportes`
2. **Generar reporte**:
   - Selecciona filtros (fechas, estado)
   - Clic en "Generar Reporte"
3. **Ver estadísticas**:
   - Totales generales
   - Distribución por estado (gráfico)
   - Tabla de pedidos filtrados
4. **Imprimir**: Usa el botón "Imprimir"

## 🔧 Configuración de Hibernate

El proyecto está configurado con:

```properties
spring.jpa.hibernate.ddl-auto=create
spring.jpa.generate-ddl=true
```

⚠️ **Importante**:

- `create`: Borra y recrea la base de datos en cada inicio (útil para desarrollo)
- `update`: Actualiza el schema sin borrar datos (recomendado para producción)
- `validate`: Solo valida el schema

Para cambiar el modo, edita `application.properties` línea 16:

```properties
spring.jpa.hibernate.ddl-auto=update  # Cambia a 'update' para producción
```

## 🎨 Tecnologías Utilizadas

- **Backend**: Spring Boot 3.x
- **Persistencia**: Hibernate/JPA
- **Base de Datos**: PostgreSQL
- **Plantillas**: Thymeleaf
- **Frontend**: Bootstrap 5.3, Bootstrap Icons
- **Gráficos**: Chart.js
- **Build Tool**: Gradle

## 📊 Entidades Principales

### PedidoEntity

- `id`: Long (PK, auto-incremento)
- `fecha`: LocalDateTime
- `estado`: String (Pendiente, En Preparación, Entregado, Pagado, Cancelado)
- `valor`: BigDecimal
- `mesa`: String
- `numeroPersonas`: Integer
- `empleado`: EmpleadoEntity (ManyToOne)
- `cliente`: ClienteEntity (ManyToOne)
- `productos`: List<PedidoProductoEntity> (OneToMany)

### PedidoProductoEntity

- `id`: Long (PK)
- `pedido`: PedidoEntity (ManyToOne)
- `producto`: ProductoEntity (ManyToOne)
- `cantidad`: Integer

## 🔐 Controladores MVC (No REST)

Todos los controladores son tradicionales `@Controller`, no `@RestController`:

- **PedidoController**: Gestión completa de pedidos
  - `GET /pedidos` - Lista de pedidos
  - `GET /pedidos/create` - Formulario crear
  - `POST /pedidos/create` - Procesar creación
  - `GET /pedidos/{id}` - Ver detalle
  - `GET /pedidos/edit/{id}` - Formulario editar
  - `POST /pedidos/edit/{id}` - Procesar edición
  - `POST /pedidos/delete/{id}` - Eliminar

- **ReporteController**: Generación de reportes
  - `GET /reportes` - Vista de reportes con filtros

## 🐛 Solución de Problemas

### La base de datos no se crea

- Verifica que PostgreSQL esté corriendo
- Confirma las credenciales en `application.properties`
- Revisa los logs de Spring Boot

### Error al crear pedidos

- Asegúrate de que existan productos y empleados en la BD
- Verifica que los IDs proporcionados sean válidos

### Los gráficos no se muestran

- Verifica que Chart.js esté cargando correctamente
- Revisa la consola del navegador por errores JavaScript

## 📞 Soporte

Para problemas o preguntas, contacta al equipo de desarrollo.

## 📄 Licencia

Este proyecto es de uso académico/interno.

---

**Desarrollado con ❤️ usando Spring Boot**
