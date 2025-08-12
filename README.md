# 🗄️ Caching Proxy Server (Spring Boot CLI)

A **Spring Boot** CLI tool that starts a caching proxy server to forward requests to an origin server and cache responses.  
If the same request is made again, the cached response is returned instead of contacting the origin.

---

## 🚀 Features
- **Forward requests** to an origin server.
- **In-memory response caching** with `X-Cache` header:
  - `HIT` → served from cache.
  - `MISS` → fetched from origin.
- **Configurable port & origin** via CLI args.
- **Clear cache** via CLI flag.

---

## 🛠 Usage

### Start the proxy server
```bash
java -jar caching-proxy.jar --port=<number> --origin=<url>
````

Example:

```bash
java -jar caching-proxy.jar --port=3000 --origin=http://dummyjson.com
```

Now:

```
http://localhost:3000/products → forwarded to http://dummyjson.com/products
```

### Clear the cache

```bash
java -jar caching-proxy.jar --clear-cache
```

---

## 📂 How It Works

1. **Startup**: Parses CLI args for `port` and `origin`, starts an embedded Tomcat server.
2. **On Request**:

   * Checks cache for the request path.
   * If cached → return with `X-Cache: HIT`.
   * If not cached → fetch from origin, store, return with `X-Cache: MISS`.
3. **Cache Clearing**: `--clear-cache` flag empties in-memory store.

---

## 🏗 Tech Stack

* **Java 17+**
* **Spring Boot**
* **RestTemplate** / **WebClient** for forwarding
* **ConcurrentHashMap** for in-memory caching

---

## 📌 Example Flow

1. First request: `/products` → MISS (stored in cache).
2. Next request: `/products` → HIT (served from cache).

---

## 🔮 Future Improvements

* Cache TTL & eviction.
* Persistent cache (Redis, file).
* Support for POST/PUT caching where applicable.

---

## 📜 License

MIT License

```
