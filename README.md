# Ear It!

A web-based ear training app for musicians. It plays musical elements through your computer's MIDI synthesizer and challenges you to identify them by ear — no guessing from notation, just listening.

## What it trains

| Mode | What you hear | What you identify |
|---|---|---|
| Notes | A single piano note | The note name (e.g. `C4`, `F#3`) |
| Chords | Notes played simultaneously | The chord type (e.g. `Minor 7th`) |
| Scales | Notes played up then back down | The scale type (e.g. `Harmonic Minor`) |
| Intervals | Two notes played in sequence | The interval (e.g. `Perfect 5th`) |

**Music theory coverage:**
- **7 chord types** — Major, Minor, Diminished, Major 7th, Dominant 7th, Minor 7th, Minor 7th Flat 5
- **7 scale types** — Major, Natural Minor, Harmonic Minor, Major Pentatonic, Minor Pentatonic, Major Blues, Minor Blues
- **24 intervals** — from Minor 2nd / Augmented 1st up to Augmented 8th, all enharmonic equivalents
- **Full piano range** — A0 to G9, randomised within a playable middle register

## How it works

1. Pick a training mode (Notes / Chords / Scales / Intervals)
2. Click **Play** — the server picks a random element and plays it via MIDI
3. Type your guess into the autocomplete field
4. For chords: use the ↑ / ↓ buttons to arpeggiate the chord as a hint
5. Click **Check** to see if you got it right

Enharmonic equivalents are accepted — `C#` and `Db` are both correct for the same note, and `Perfect 5th` / `Diminished 6th` are both accepted for the same interval.

## Tech stack

- **Java 21 / Spring Boot 3.3** — REST API and Thymeleaf-rendered UI
- **javax.sound.midi** — built-in Java MIDI synthesizer, no external audio dependencies
- **springdoc-openapi** — auto-generated interactive API docs (Swagger UI)
- **Spring Actuator** — health and info endpoints
- **jQuery + jQuery UI** — autocomplete, UI interactions
- **Maven** — build and dependency management

## Running locally

Prerequisites: Java 18+, Maven, a system with MIDI/audio support.

```bash
mvn spring-boot:run
```

Then open: [http://localhost:8080/earit/ui/index](http://localhost:8080/earit/ui/index)

| Endpoint | URL |
|---|---|
| App UI | `http://localhost:8080/earit/ui/index` |
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| OpenAPI JSON | `http://localhost:8080/v3/api-docs` |
| Health check | `http://localhost:8080/actuator/health` |
| App info | `http://localhost:8080/actuator/info` |

> Audio plays through the **server's** sound hardware — run locally, not on a remote server.

---

## API

All endpoints return `application/json`. The player endpoints (`/randomizer/` and `/basic-player/`) also produce audio through the MIDI synthesizer as a side effect.

### Response shapes

**EarItPlayerResponse** (returned by all player endpoints):
```json
{
  "midiNote": {
    "shortName1": "string",
    "shortName2": "string"
  },
  "chordType": "string | null",
  "scaleType": "string | null",
  "intervalContainer": {
    "intervals": ["string"]
  },
  "status": "SUCCESS | FAILED"
}
```

- `midiNote.shortName1` — primary note name (e.g. `"C#4"`)
- `midiNote.shortName2` — enharmonic equivalent if the note is an accidental (e.g. `"Db4"`); empty string `""` for natural notes
- `intervalContainer.intervals` — all interval names that share the same number of semitones (e.g. `["Perfect 5th", "Diminished 6th"]`); any of these is accepted as a correct answer
- Fields irrelevant to the played element are `null` (e.g. a note response has `chordType: null`)
- On error: all fields are `null` and `status` is `"FAILED"`, HTTP 400

---

### Options endpoints

Used by the UI to populate autocomplete dropdowns. No audio is played.

---

#### `GET /earit/options/notes`

Returns all valid note names (every MIDI note A0–G9, including both enharmonic spellings for accidentals).

```
GET /earit/options/notes
```

**Response 200:**
```json
["A0", "A#0", "Bb0", "B0", "C1", "C#1", "Db1", "D1", "D#1", "Eb1", "E1", "F1", "F#1", "Gb1", "G1", "G#1", "Ab1", "A1", "A#1", "Bb1", "B1", "C2", "C#2", "Db2", "D2", "D#2", "Eb2", "E2", "F2", "F#2", "Gb2", "G2", "G#2", "Ab2", "A2", "A#2", "Bb2", "B2", "C3", "C#3", "Db3", "D3", "D#3", "Eb3", "E3", "F3", "F#3", "Gb3", "G3", "G#3", "Ab3", "A3", "A#3", "Bb3", "B3", "C4", "C#4", "Db4", "D4", "D#4", "Eb4", "E4", "F4", "F#4", "Gb4", "G4", "G#4", "Ab4", "A4", "A#4", "Bb4", "B4", "C5", "C#5", "Db5", "D5", "D#5", "Eb5", "E5", "F5", "F#5", "Gb5", "G5", "G#5", "Ab5", "A5", "A#5", "Bb5", "B5", "C6", "C#6", "Db6", "D6", "D#6", "Eb6", "E6", "F6", "F#6", "Gb6", "G6", "G#6", "Ab6", "A6", "A#6", "Bb6", "B6", "C7", "C#7", "Db7", "D7", "D#7", "Eb7", "E7", "F7", "F#7", "Gb7", "G7", "G#7", "Ab7", "A7", "A#7", "Bb7", "B7", "C8", "C#8", "Db8", "D8", "D#8", "Eb8", "E8", "F8", "F#8", "Gb8", "G8", "G#8", "Ab8", "A8", "A#8", "Bb8", "B8", "C9", "C#9", "Db9", "D9", "D#9", "Eb9", "E9", "F9", "F#9", "Gb9", "G9"]
```

---

#### `GET /earit/options/chordTypes`

Returns all valid chord type names.

```
GET /earit/options/chordTypes
```

**Response 200:**
```json
["Major", "Minor", "Diminished", "Major 7th", "Dominant 7th", "Minor 7th", "Minor 7th Flat 5"]
```

---

#### `GET /earit/options/scaleTypes`

Returns all valid scale type names.

```
GET /earit/options/scaleTypes
```

**Response 200:**
```json
["Major", "Natural Minor", "Harmonic Minor", "Major Pentatonic", "Minor Pentatonic", "Major Blues", "Minor Blues"]
```

---

#### `GET /earit/options/intervals`

Returns all valid interval names.

```
GET /earit/options/intervals
```

**Response 200:**
```json
["Perfect 4th", "Perfect 5th", "Perfect 8th", "Major 2nd", "Major 3rd", "Major 6th", "Major 7th", "Minor 2nd", "Minor 3rd", "Minor 6th", "Minor 7th", "Augmented 1st", "Augmented 2nd", "Augmented 3rd", "Augmented 4th", "Augmented 5th", "Augmented 6th", "Augmented 7th", "Augmented 8th", "Diminished 3rd", "Diminished 4th", "Diminished 5th", "Diminished 6th", "Diminished 7th", "Diminished 8th"]
```

---

### Randomizer endpoints

Each call picks a random musical element, plays it, and returns what was played (for the UI to check the user's guess against). No request parameters.

---

#### `GET /earit/randomizer/note`

Plays a random note.

```
GET /earit/randomizer/note
```

**Response 200** (example — C#4/Db4 was played):
```json
{
  "midiNote": {"shortName1": "C#4", "shortName2": "Db4"},
  "chordType": null,
  "scaleType": null,
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

**Response 200** (example — E4 was played, a natural note):
```json
{
  "midiNote": {"shortName1": "E4", "shortName2": ""},
  "chordType": null,
  "scaleType": null,
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

---

#### `GET /earit/randomizer/chord`

Plays a random chord (all notes simultaneously).

```
GET /earit/randomizer/chord
```

**Response 200** (example — E4 Minor was played):
```json
{
  "midiNote": {"shortName1": "E4", "shortName2": ""},
  "chordType": "Minor",
  "scaleType": null,
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

---

#### `GET /earit/randomizer/ascendingArpeggio`

Plays the notes of a random chord one at a time, lowest to highest.

```
GET /earit/randomizer/ascendingArpeggio
```

**Response 200** (example — A3 Major 7th was played):
```json
{
  "midiNote": {"shortName1": "A3", "shortName2": ""},
  "chordType": "Major 7th",
  "scaleType": null,
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

---

#### `GET /earit/randomizer/descendingArpeggio`

Plays the notes of a random chord one at a time, highest to lowest.

```
GET /earit/randomizer/descendingArpeggio
```

**Response 200** (example — G4 Diminished was played):
```json
{
  "midiNote": {"shortName1": "G4", "shortName2": ""},
  "chordType": "Diminished",
  "scaleType": null,
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

---

#### `GET /earit/randomizer/scale`

Plays a random scale ascending then descending.

```
GET /earit/randomizer/scale
```

**Response 200** (example — D4 Harmonic Minor was played):
```json
{
  "midiNote": {"shortName1": "D4", "shortName2": ""},
  "chordType": null,
  "scaleType": "Harmonic Minor",
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

---

#### `GET /earit/randomizer/interval`

Plays a random interval (root note, then the second note after a short gap).

```
GET /earit/randomizer/interval
```

**Response 200** (example — F4 Perfect 5th was played):
```json
{
  "midiNote": {"shortName1": "F4", "shortName2": ""},
  "chordType": null,
  "scaleType": null,
  "intervalContainer": {"intervals": ["Perfect 5th", "Diminished 6th"]},
  "status": "SUCCESS"
}
```

> `intervalContainer.intervals` lists all interval names that are enharmonically equivalent (same number of semitones). The UI accepts any of them as a correct answer.

---

### Basic player endpoints

Play a specific element by name. Used by the UI to replay the same element after the randomizer reveals it.

---

#### `GET /earit/basic-player/note`

| Parameter | Type | Description |
|---|---|---|
| `note` | string | Note short name, e.g. `C4`, `F#4`, `Bb3` |

```
GET /earit/basic-player/note?note=C4
```

**Response 200** (natural note):
```json
{
  "midiNote": {"shortName1": "C4", "shortName2": ""},
  "chordType": null,
  "scaleType": null,
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

```
GET /earit/basic-player/note?note=F%234
```

**Response 200** (accidental note — `F%234` is URL-encoded `F#4`):
```json
{
  "midiNote": {"shortName1": "F#4", "shortName2": "Gb4"},
  "chordType": null,
  "scaleType": null,
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

**Response 400** (blank or unrecognised note):
```json
{
  "midiNote": null,
  "chordType": null,
  "scaleType": null,
  "intervalContainer": null,
  "status": "FAILED"
}
```

---

#### `GET /earit/basic-player/chord`

| Parameter | Type | Description |
|---|---|---|
| `rootNote` | string | Root note short name, e.g. `C4` |
| `type` | string | Chord type name, e.g. `Major 7th` (case-insensitive) |

```
GET /earit/basic-player/chord?rootNote=C4&type=Major+7th
```

**Response 200:**
```json
{
  "midiNote": {"shortName1": "C4", "shortName2": ""},
  "chordType": "Major 7th",
  "scaleType": null,
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

**Response 400** (blank or unrecognised parameter):
```json
{
  "midiNote": null,
  "chordType": null,
  "scaleType": null,
  "intervalContainer": null,
  "status": "FAILED"
}
```

---

#### `GET /earit/basic-player/ascendingArpeggio`

| Parameter | Type | Description |
|---|---|---|
| `rootNote` | string | Root note short name, e.g. `A3` |
| `type` | string | Chord type name (case-insensitive) |

```
GET /earit/basic-player/ascendingArpeggio?rootNote=A3&type=Minor
```

**Response 200:**
```json
{
  "midiNote": {"shortName1": "A3", "shortName2": ""},
  "chordType": "Minor",
  "scaleType": null,
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

**Response 400** (blank or unrecognised parameter):
```json
{
  "midiNote": null,
  "chordType": null,
  "scaleType": null,
  "intervalContainer": null,
  "status": "FAILED"
}
```

---

#### `GET /earit/basic-player/descendingArpeggio`

| Parameter | Type | Description |
|---|---|---|
| `rootNote` | string | Root note short name, e.g. `G4` |
| `type` | string | Chord type name (case-insensitive) |

```
GET /earit/basic-player/descendingArpeggio?rootNote=G4&type=Dominant+7th
```

**Response 200:**
```json
{
  "midiNote": {"shortName1": "G4", "shortName2": ""},
  "chordType": "Dominant 7th",
  "scaleType": null,
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

**Response 400** (blank or unrecognised parameter):
```json
{
  "midiNote": null,
  "chordType": null,
  "scaleType": null,
  "intervalContainer": null,
  "status": "FAILED"
}
```

---

#### `GET /earit/basic-player/scale`

| Parameter | Type | Description |
|---|---|---|
| `rootNote` | string | Root note short name, e.g. `D4` |
| `type` | string | Scale type name (case-insensitive) |

```
GET /earit/basic-player/scale?rootNote=D4&type=Natural+Minor
```

**Response 200:**
```json
{
  "midiNote": {"shortName1": "D4", "shortName2": ""},
  "chordType": null,
  "scaleType": "Natural Minor",
  "intervalContainer": null,
  "status": "SUCCESS"
}
```

**Response 400** (blank or unrecognised parameter):
```json
{
  "midiNote": null,
  "chordType": null,
  "scaleType": null,
  "intervalContainer": null,
  "status": "FAILED"
}
```

---

#### `GET /earit/basic-player/interval`

| Parameter | Type | Description |
|---|---|---|
| `rootNote` | string | Root note short name, e.g. `F4` |
| `intervalToPlay` | string | Interval name (case-insensitive) |

```
GET /earit/basic-player/interval?rootNote=F4&intervalToPlay=Perfect+5th
```

**Response 200:**
```json
{
  "midiNote": {"shortName1": "F4", "shortName2": ""},
  "chordType": null,
  "scaleType": null,
  "intervalContainer": {"intervals": ["Perfect 5th", "Diminished 6th"]},
  "status": "SUCCESS"
}
```

> Even when specifying an interval by name, the response `intervalContainer.intervals` lists all enharmonic equivalents (same semitone count). For `Perfect 5th` (7 semitones), that is `["Perfect 5th", "Diminished 6th"]`.

**Response 400** (blank or unrecognised parameter):
```json
{
  "midiNote": null,
  "chordType": null,
  "scaleType": null,
  "intervalContainer": null,
  "status": "FAILED"
}
```
