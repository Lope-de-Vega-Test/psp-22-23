![lope_logo](https://www.ceslopedevega.com/wp-content/uploads/2020/03/pruebalogo.svg_.png)

# INSTRUCCIONES ENTREGA

En este documento se explica el procedimiento para realizar las entregas y las correcciones de ejercicios, prácticas y exámenes para la asignatura durante el cursos 2022/2023.

[CFGS Desarrollo de Aplicaciones Multiplaforma.](https://www.ceslopedevega.com/grado-superior-desarrollo-de-aplicaciones-multiplataforma/)
[CES Lope de Vega](https://www.ceslopedevega.com/) en Córdoba (Andalucía, Spain).




## Introducción

El procedimiento para la entrega y evaluación de prácticas estará totalmente centralizado en **GitHub**.

Para ello, se ha diseñado un procedimiento automático de entrega y testeo del código entregado, de tal manera, el alumno podrá saber en el momento de la entrega la nota conseguida, es más, lo sabrá antes de realizar la entrega.

Los pasos básicos a seguir son:

1. **Registrar** un usuario en GitHub y solicitar acceso al repositorio, en adelante USERNAME.
2. Crear una **rama** con el nombre del usuario asociado al repositorio.
3. Se creará una **carpeta** con el nombre del usuario en dicha rama del repositorio.
4. El usuario **ÚNICAMENTE** podrá crear ficheros en dicha rama y carpeta del repositorio. Ver [Penalizaciones](#Penalizaciones).
5. Una vez desarrollado el código, se ejecuctarán los Tests que indicarán si se han superado los requerimientos y la nota asociada.  Ver [Ejecución de Tests](#ejecución-de-tests)



## Ejecución de Tests

Todo el código a desarrollar en la asignatura está basado en Java, concretamente en la **versión 17.**.

Por ello para la realización de tests unitarios se utilizará el framework [Junit](https://junit.org/junit5/).

Se dispondrá de un programa (script) que, tomando como argumentos el usuario de github y la práctica a evaluar, ejecutará los tests automáticos informando al desarrollador del éxito o fracaso de los tests y de la nota asociada al trabajo en cuestión.

    tester USERNAME UAX_PX

La idea bajo este modelo de trabajo es **acercarnos**, repito, **acercarnos** a los actuales modelos de trabajo basados en Integración Continua y Desarrollo Continuo: [CI/CD](https://docs.gitlab.com/ee/ci/introduction/) en entornos y equipos multidisciplinares y heterogéneos.

En la fecha de entrega predeterminada, se realizará una integración **AUTOMÁTICA** de todas las ramas en una rama común, donde se ejecutarán los **MISMOS TESTS** a todos los usuarios, determinando la nota final obtenido en el trabajo.

También se realizará un procedimiento automático de control del plagio sobre el código fuente subido por todos los USUARIOS.

## Penalizaciones
### Suspenso Automático de la Práctica:
1. Crear ficheros fuera de la carpeta asignada al usuario.
2. Resultados altos en las herramientas de comparación automática de código

### Penalización en Puntos
1. Subida de ficheros binarios (compilados) al repositorio.
2. Subida de ficheros de configuración de entornos de desarrollo al repositorio.