import { useParams } from 'react-router-dom'
import TestDetails from './TestDetails'

export default function TestPage() {
  return (
    <TestDetails {...useParams} />)
}
