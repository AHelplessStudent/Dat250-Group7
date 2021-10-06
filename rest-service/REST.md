# Rest-API Overview

Polls can be accessed through `/polls`. Votes can be accessed through `/votes` or `/polls/{pid}/votes`.

Syntax:

* `/polls/{pid}` - user should enter pid
* `/polls/(a, b, c)` - user selects between the options (fields) a, b and c
    * Short-hand for `/polls/a` , `/polls/b` , `/polls/c`

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

```
/polls/{pid}/(title, deadline, public)
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

```
/polls/{pid}/votes/{vid}/(num_yes, num_no)
```

## POST

```
/polls
```

```
/polls/{pid}/votes
```
