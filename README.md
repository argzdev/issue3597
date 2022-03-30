# issue 3597

### Prerequisite
- Add google-services.json
- Enable Firebase Realtime Database from Firebase console
### How to use
- Open app
- Click `Add Data` button to populate data
- Once the toast message `Update complete` shows up, click `Query` button to show the result
### Summary
- The RTDB contains the following values below:
```
  testRef.setValue(mapOf(
      "yo" to mapOf("search" to 2),
      "so" to mapOf("search" to 3),
      "to" to mapOf("search" to 4),
      "fu" to mapOf("search" to 1),
      "bo" to mapOf("search" to 5),
  )) 
```

Actual output:
```
{search: 3}
{search: 4}
{search: 5}
```

Expected output:
```
{search: 4}
{search: 5}
```

- Using `testRef.orderByChild("search").startAfter((3).toDouble()).get()` should only show 2 search result instead of 3, since the `startAfter` should only get numbers that are after the number (e.g. 3).


P.S. I've included a Java and Kotlin version implementation of the issue. Can be interchangeable in the AndroidManifest.xml
