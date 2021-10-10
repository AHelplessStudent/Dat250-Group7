# Rest-API Overview

Polls can be accessed through `/polls`. Votes can be accessed through `/polls/{pid}/votes` or `/votes`. We originally
thought of not doing a `/votes`-API, but later found out it might make sense anyway.

## GET

```
/polls
```

```
/polls/{pid}
```

```
/polls/{pid}/votes
```

```
/votes
```

```
/votes/{vid}
```

## DELETE

```
/polls/{pid}
```

```
/votes/{vid}
```

## PUT

```
/polls/{pid}
```

```
/votes/{vid}
```

## POST

```
/polls
```

```
/votes
```
