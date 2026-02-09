# Copilot Code Review Instructions

## General
- Check for potential security issues (e.g., SQL injection, hard-coded secrets)
- Favor readability over clever code
- Ensure methods and classes have meaningful names
- Detect for-loops over collections and, if they are only used to transform, filter, collect, or accumulate, suggest using streams/collectors or other higher-level APIs instead.
- Provide all relevant suggestions in one review
- Only provide suggestions and review comments. Do not create new pull requests or modify code automatically.

## Review Tone
- Be concise
- Give examples when suggesting changes
- Suggest alternatives politely
