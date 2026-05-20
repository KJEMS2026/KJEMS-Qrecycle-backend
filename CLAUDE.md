# KJEMS Qrecycle — Projektdokumentation

## Projektstruktur

To separate repos:
- **Backend:** `KJEMS-Qrecycle-backend` — Java, Spring Boot, JPA, MySQL
- **Frontend:** `KJEMS-Qrecycle-frontend` — Vanilla JS (ES modules), HTML, CSS

## Tech Stack

- **Backend:** Java, Spring Boot, JPA/JpaRepository, MySQL
- **Frontend:** Vanilla JavaScript (ES modules), HTML, CSS
- **Auth:** Supabase — frontend henter brugerrolle fra tabellen `user` og router til korrekt view
- **Versionsstyring:** GitHub med branch protection
- **Containerisering:** Docker

## Brugerroller

`DRIVER`, `COMPANY`, `ADMIN`

## Backend-konventioner

- Al chaufføroplogik ligger i `DriverService` / `DriverController`
- Services implementerer et interface (`IDriverService` osv.)
- DTOs bruges til at eksponere data — aldrig rå entiteter
- `bagsCollected = null` betyder ikke afhentet endnu (bruges som aktiv-filter)

## Frontend-konventioner

- Én `index.html` — alle views renderes dynamisk i `.content`-div
- Hvert view er en funktion i den tilsvarende JS-fil (`driverView()`, `companyView()`, `adminView()`)
- **CSS:** Opret altid en ny `.css`-fil pr. feature — skriv aldrig i eksisterende filer
- CSS-filer linkes i `index.html`

## Chauffør-flow (K2-11)

| Screen | Funktion | Beskrivelse |
|--------|----------|-------------|
| C2 | `showDashboard()` | Dashboard med "Se dagens rute" |
| C3 | `showRouteList()` | Liste af stops, mulighed for at fjerne |
| C4 | `showRouteMap()` | Kort med optimeret rute + to start-knapper |
| C5A | `showActiveRouteEmbed()` | Tilgang A — Google Maps iframe embed |
| C5B | `showActiveRoute()` | Tilgang B — Indbygget JS API med live navigation |

C4 har to knapper: **🗺 Google Maps** (iframe) og **📍 Indbygget** (JS API). Begge er implementeret så man kan teste hvilken der virker bedst.

## Backend endpoint — chaufførrute

```
GET /driver/route
```

Returnerer `List<ActivePickupRequestDTO>` med `companyName`, `address`, `bagsToBeCollected`, `createdAt` for alle `PickupRequest` hvor `bagsCollected IS NULL`.

## Google Maps

- Bruger **Routes API v2** (`routes.googleapis.com/directions/v2:computeRoutes`) — ikke den forældede `DirectionsService`
- Bruger `AdvancedMarkerElement` — kræver `mapId: 'DEMO_MAP_ID'` på kortet
- Indlæses med `loading=async&libraries=geometry,marker&callback=__mapsReady`
- API-nøgle ligger i konstanten `MAPS_KEY` øverst i `js/driver.js` i frontend-repo'et
- **Routes API skal aktiveres separat** i Google Cloud Console (udover Maps JavaScript API)
- `languageCode: 'da'` sikrer danske vejinstruktioner fra API'et
- Field mask inkluderer steps til turn-by-turn navigation: `routes.legs.steps.navigationInstruction`, `routes.legs.steps.startLocation`, `routes.legs.steps.distanceMeters`

## Hvad mangler (åbne tickets)

- "Marker stop som afhentet" — UI er klar i C5, backend endpoint mangler
- "+ Omkostning" — separat feature
- Navigér videre til næste stop efter afhentning