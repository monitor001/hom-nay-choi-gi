package com.gaucon.domain.model

enum class Domain {
    PHYSICAL,
    COGNITIVE,
    LANGUAGE,
    SOCIAL_EMOTIONAL,
    AESTHETIC,
    SELF_CARE,
}

enum class Feedback {
    LIKED,
    NEUTRAL,
    NOT_FIT,
}

enum class ReminderType {
    DAILY_ACTIVITY,
    ROUTINE,
    HEALTH_CHECKUP,
    MILESTONE_CHECK,
    JOURNAL_WEEKLY,
}

enum class ReminderAction {
    SHOWN,
    OPENED,
    SNOOZED,
    DONE,
    DISMISSED,
    SKIPPED_RULE,
}

enum class ContentStatus {
    DRAFT_UNREVIEWED,
    REVIEWED,
}
