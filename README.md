# Ticket Management System

A Java console application for managing ticket records using hand-written data structures.

Built for a **Data Structures** course at UiTM.

## Features

- Load ticket records from `ticketdata.txt`
- View, add, delete and search records
- Two implementations of the same system:
  - **Linked list** (`TicketLL`, `TicketLLApp`)
  - **Queue** (`Queue`, `TicketMainQueue`, `mainAppQueue`)
- Custom `LinkedList` and `Node` classes, no Java collections

## Tech stack

Java (developed in BlueJ)

## Running

```bash
cd src
javac *.java
java TicketLLApp        # linked list version
java TicketMainQueue    # queue version
```
