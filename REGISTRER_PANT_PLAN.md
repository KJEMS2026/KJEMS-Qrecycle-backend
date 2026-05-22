# Feature: Registrer Pant (K2-11)

## Overblik

Chaufføren er i C5 (aktiv kørsel) og skal kunne markere et stop som afhentet.
Det udfylder de resterende `null`-felter på `PickupRequest` i databasen.

---

## Felter der skal udfyldes på PickupRequest

| Felt            | Type            | Beskrivelse                                      |
|-----------------|-----------------|--------------------------------------------------|
| `bagsCollected` | `Integer`       | Chauffør taster selv antal — behøver ikke matche `bagsToBeCollected` |
| `dateCollected` | `LocalDateTime` | Sættes til tidspunktet for afhentning            |
| `user`          | `User`          | Den chauffør der foretog afhentningen (til Admin-overblik) |

---

## Flow

```
C5 (aktiv kørsel)
 └─ Chaufføren ser første stop
 └─ Udfylder antal poser i form
 └─ Trykker "Markér som afhentet"
      └─ C6 (bekræftelsesview) vises med:
            - Virksomhedsnavn
            - Adresse
            - Antal poser (det chaufføren tastede)
            - Tidspunkt
      └─ Chauffør trykker "Ja, markér som afhentet"
            └─ Backend opdaterer PickupRequest (bagsCollected, dateCollected, user_id)
            └─ Ruten genstartes/genberegnes mod næste stop
```

---

## Åbne spørgsmål (ikke afklaret endnu)

- **Rutegenberegning:** Skal "Ja, markér som afhentet" også trigge genberegning af ruten automatisk, eller skal det være en separat knap?
- **user_id:** Hvordan ved backenden hvilken chauffør der er logget ind? Sendes user_id med fra frontend, eller hentes det fra session/token?

---

## Backend

### Nyt endpoint (skal laves)

```
PATCH /driver/route/{id}/collect
```

Request body (forslag):
```json
{
  "bagsCollected": 5,
  "userId": 3
}
```

Backend sætter selv `dateCollected = LocalDateTime.now()`.

### PickupRequest entity (eksisterende felter)

- `bagsCollected` — `Integer` (null = ikke afhentet)
- `dateCollected` — `LocalDateTime`
- `user` — `@ManyToOne` relation til `User`

---

## Frontend

- C5 har en form hvor chaufføren taster antal poser
- Efter submit vises C6 (bekræftelsesview)
- C6 viser: virksomhedsnavn, adresse, antal poser, tidspunkt
- Knap: "Ja, markér som afhentet" → kalder backend → genstart rute mod næste stop

---

## Næste skridt når vi fortsætter

1. Afklar de åbne spørgsmål ovenfor
2. Lav backend endpoint (`PATCH /driver/route/{id}/collect`)
3. Lav Service-metode og DTO
4. Lav frontend C6-view
5. Kobl "Ja"-knap til backend-kald og rutegenstart