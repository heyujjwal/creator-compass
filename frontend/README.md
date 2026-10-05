# Creator Compass

A private, local-first AI advisor for creators who don't want
content creation to take over their real life.

## Problem

Creators often struggle with:

- What should I post today?
- Is this content actually aligned with my personality?
- Should I follow this trend?
- Am I posting too much?
- Is creating content hurting my studies/work?
- What can I create when I have only 20 minutes?

Creator Compass answers those questions based on the creator's
current mood, day, available time and content history.

## Why Open AI?

Creator advice can contain highly personal information,
including private life, emotions and academic/research details.

Creator Compass uses a locally running open-weight model through
Ollama so the application can work without sending this data
to a third-party AI API.

The model can also be swapped without changing the application.

## Architecture

React
↓
Spring Boot
↓
Ollama
↓
Open-weight LLM

## Features

- Mood-aware content recommendations
- Time-aware content suggestions
- PhD/work-life protection
- Content pillar awareness
- Low-effort alternatives
- "Don't post" recommendations
- Local inference
- No external AI API required