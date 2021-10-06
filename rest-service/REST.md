# Rest-API Overview

Polls can be accessed through `/polls`. Votes can be accessed through `/polls/{pid}/votes`. We originally thought of
doing a `/votes`-API, but redeemed it to unpractical for this particular experiment.

Syntax:

* `/polls/{pid}` - user should enter pid

## GET

```
/polls/
```

```
/polls/{pid}
```

```
/polls/{pid}/votes
```

```
/polls/{pid}/votes/{vid}
```

## DELETE

```
/polls/{pid}
```

```
/polls/{pid}/votes/{vid}
```

## PUT

```
/polls/{pid}
```

```
/polls/{pid}/(title, deadline, public)
```

```
/polls/{pid}/votes/{vid}
```

## POST

```
/polls
```

```
/polls/{pid}/votes
```
