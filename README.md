# 🏥 SehatSetu (SIH 2026 · Problem Statement SIH25018)
> **An Offline-First, Low-Bandwidth Telemedicine Solution for Rural Healthcare in Nabha, Punjab.**

SehatSetu bridges the infrastructure gap in digital healthcare by enabling community ASHA workers to capture patient diagnostics in complete network blackouts, asynchronously syncing records over erratic 2G/3G pulses, and executing optimized video channels.

## 🛠️ Complete Project Architecture Layout
* **frontend-mobile/**: Multi-page cross-platform mobile client engineered natively using **Flutter**.
  * Encrypted Local Caching Node using **SQLCipher & SQLite (AES-256 Bit Encryption)**.
  * Throttled **WebRTC Engine** configured to force extreme network constraints (**50 Kbps, 160p @ 8FPS**).
  * Automated background network polling framework via **Background Fetch**.
* **demo_backend-server/**: Enterprise-grade system infrastructure core powered by **Java & Spring Boot**.
  * REST API Controller Interceptors mapping live village clinic pharmacy inventories.
  * Dynamic Generic Drug Substitution Engine preventing empty prescription fulfillments.
* **doctor_dashboard.html**: Light weight single-page centralized medical monitoring dashboard website for clinical desktop terminals.

## 🚀 Live presentation Execution Checklist
1. Open your terminal inside `demo_backend-server/` and start your Java server container: `./mvnw spring-boot:run`
2. Double-click the `doctor_dashboard.html` file to launch the doctor's web workspace interface in Google Chrome.
3. Boot up the mobile client inside `frontend-mobile/` using `flutter run` on your testing phone emulator canvas.
4. Toggle Airplane Mode on the mobile terminal to demonstrate encrypted offline data capture, then turn it off to witness automatic background data synchronization across devices live!
