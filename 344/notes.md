# Prog. Languages - CMPS 344

First Day -- Intro -- Read Chapters 1 & 2, bs bout' syllabus



```
read
while (c) {
  process
  read
}
TL;DR Functional > Every other paradigm
```





# Chapter X

## Variable Attributes - Scope

 - Range of statemtns over which it is visible
- local variables of a program unit are those that are declared in that unit

## Dynamic Scope

- XXX

## Referencing Environments

- Referencing environment of a statement is the collection of all names that are visible in the statement
- in static-scoped language, is the local variables plus all of the visible variables in all enclosing scopes
- subprogram is active if its execution has begun but has not yet terminated
- in a dynamic language, the referencing environment is the local variables plus all visible variables in all active subprograms

## TypeChecking

- checking operands of operations
- ​


# Introduction to Syntax

- **Sentence**: string of characters over some alphabet
- **Language:** is the lowest level syntactic unit of a language
- **Lexeme:** is the lowest level syntactic unit of a language (e.g. sum, begin)
- **Token:** category of lexemes (e.g. identifier) OR a *set* of lexemes

## Recognizers

- Recognition device reads input strings over the alphabet of the language and decides whether the input strings belong to the language

## Generators

- A device that generates sentences of a language
- Can determine if the syntax of a particular sentence is syntactically correct by comparing it to the structure of the generator



### Context-Free Grammars

- Developed by Noam Chomsky in the mid-1950s
- Language generators, meant to describe the syntax of natural languages
- Define a class of languages called context-free languages

### Backus-Naur Form (1959)

- Invented by John Backus to describe the syntax of Algol 58
- BNF is equivalent to context-free grammars

### Semantic analysis

- I.E: *Meaning of things*
  - `float i = 17;`
    - coerced to mean 17.00 (a floating point integer)
  - `int i = 17.0;`
    - will take it to mean 17 and not a floating point integer/decimal



(Empty = Errors)

| I                   | "        | \        | '        | *        |
| ------------------- | -------- | -------- | -------- | -------- |
| START               | InString | Start    | InChar   | Start    |
| InString            | Start    | BSS      | InString | InString |
| Backslash Str (BSS) | InString | InString | InString | InString |
| InChar              | GotChar  | BSC      | GotChar  | GotChar  |
| Backslash           | GotChar  | GotChar  | GotChar  | GotChar  |
| Got Char            |          |          | Start    |          |
| Error               |          |          |          |          |

