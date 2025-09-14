# hamaga-order
Is a repository to practice spring reactor

📌 Requisitos principales:

✅ 1. Recepción y procesamiento de pedidos:

Implementa un endpoint POST /orders que reciba un pedido en formato JSON.

Los pedidos deben ser validados de manera reactiva.

Una vez validados, los pedidos se procesan de manera asincrónica.

✅ 2. Flujo de notificación de estado en tiempo real:

Implementa un endpoint GET /orders/{customerId}/status que permita a los clientes conectarse mediante Server-Sent Events (SSE) para recibir actualizaciones del estado de sus pedidos en tiempo real.

✅ 3. Persistencia reactiva:

Usa R2DBC (Reactive Relational Database Connectivity) para almacenar los pedidos en una base de datos relacional (PostgreSQL, MySQL, etc.).

Cada pedido debe tener un estado (por ejemplo: PENDING, PROCESSING, SHIPPED, DELIVERED).

✅ 4. Manejo de errores y resiliencia:

Implementa un Global Error Handler reactivo para que las excepciones sean gestionadas de manera uniforme y no bloqueen el procesamiento.

Asegúrate de que el sistema siga funcionando correctamente incluso si fallan componentes como la base de datos o las notificaciones.

💡 Extras (Opcional, para ir más allá):

Integra Kafka o RabbitMQ para la publicación y el consumo de eventos de pedidos.

Implementa un mecanismo de back-pressure para evitar la sobrecarga del sistema.

Usa Spring Cloud Gateway para exponer la API y aplicar filtros reactivos.

Escribe pruebas unitarias y de integración con WebTestClient y bases de datos embebidas como Testcontainers.

🧩 Lo que se espera de ti:

🔷 Código limpio, siguiendo principios de programación reactiva y arquitectura limpia.
🔷 Uso adecuado de operadores de Reactor (flatMap, concatMap, switchIfEmpty, retryWhen, etc.).
🔷 Solución escalable y mantenible.
🔷 Documentación breve (README o comentarios en el código) explicando las decisiones técnicas.
